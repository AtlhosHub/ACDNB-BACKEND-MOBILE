import pandas as pd
import unicodedata
import re
import chardet
import requests

from pathlib import Path

TOKEN = ""

def detectar_encoding(caminho_arquivo):
    with open(caminho_arquivo, "rb") as f:
        resultado = chardet.detect(f.read())

    return resultado["encoding"]

def remover_acentos_unicode(texto):
    texto_normalizado = unicodedata.normalize("NFD", texto)
    texto_sem_acentos = re.sub(r"[\u0300-\u036f]", "", texto_normalizado)
    return texto_sem_acentos

# def get_nome_cidade(id_cidade):
#     if id_cidade in codigo_cidade_nome:
#         return codigo_cidade_nome[id_cidade]

#     url = f"https://servicodados.ibge.gov.br/api/v1/localidades/municipios/{id_cidade}"
#     response = requests.get(url)
#     if response.status_code == 200:
#         data = response.json()
#         nome = data.get("nome")
#         codigo_cidade_nome[id_cidade] = nome
#         return nome
#     return None

# def get_coordenadas_cidade(df_cidade):
#     estado = df_cidade["estado"]
#     nome_cidade = df_cidade["nome_cidade"]
#     url = f"https://nominatim.openstreetmap.org/search?state={estado}&city={nome_cidade}&format=json"
#     response = requests.get(url)
#     if response.status_code == 200:
#         data = response.json()
#         lat = data[0].get("lat")
#         lon = data[0].get("lon")
#         return lat, lon
#     return None

def ler_arquivo_csv(caminho_arquivo, sep):
    encoding = detectar_encoding(arquivo_censo)
    print("Encoding detectado:", encoding)
    return pd.read_csv(caminho_arquivo, encoding=encoding, sep=sep)

def transformar_dados(df_dados, df_cidades):

    df = df_dados.copy()

    COL_RENAME = {
        "CD_MUN": "id",
        "NM_MUN": "nome_municipio",
        "V06001": "num_responsaveis",
        "V06002": "num_habitantes",
        "V06003": "var_num_habitantes",
        "V06004": "renda_media_responsavel",
        "V06005": "var_renda_responsavel",
    }

    for col in df.columns:
        if col in COL_RENAME:
            df.rename(columns={col: COL_RENAME[col]}, inplace=True)

    # Filtrar apenas os municípios de São Paulo (código IBGE começa com 35)
    sp_mask_agregado = df["id"].astype(str).str.startswith("35")
    sp_mask_cidades = df_cidades["codigo_ibge"].astype(str).str.startswith("35")
    df = df.loc[sp_mask_agregado]
    df_cidades = df_cidades.loc[sp_mask_cidades]

    df["nome_municipio"] = df["nome_municipio"].apply(remover_acentos_unicode)

    df["nome_municipio"] = (
        df["nome_municipio"]
        .astype(str)
        .str.replace(r"[\n\r\t]", " ", regex=True)
        .str.replace(r"\s+", " ", regex=True)
        .str.strip()
    )

    for col in df.columns:
        if col != "nome_municipio" and col != "id":
            df[col] = df[col].replace({",": "."}, regex=True)
            df[col] = pd.to_numeric(df[col], errors="coerce")

    df["id"] = df["id"].astype(str)
    df_cidades["codigo_ibge"] = df_cidades["codigo_ibge"].astype(str)

    df = df.merge(df_cidades[["codigo_ibge", "latitude", "longitude"]], left_on="id", right_on="codigo_ibge", how="left")

    df.drop(columns=["codigo_ibge"], inplace=True)

    return df

def salvar_dados(data, token):
    response = requests.post("http://localhost:8080/censo/importar", json=data, headers={"Authorization": f"Bearer {token}"})

    if response.status_code != 200:
        raise Exception(response.text)

def chunked(data, size=500):
    for i in range(0, len(data), size):
        yield data[i : i + size]

if __name__ == "__main__":

    arquivo_censo = Path.cwd() / "Agregados_por_municipios_renda_responsavel_BR.csv"
    arquivo_cidades = Path.cwd() / "cidades.csv"


    df_dados = ler_arquivo_csv(arquivo_censo, ";")
    df_cidades = ler_arquivo_csv(arquivo_cidades, ",")
    
    df = transformar_dados(df_dados, df_cidades)
    
    token = TOKEN if TOKEN else input("Insira o token de autenticação: ")
    
    for batch in chunked(df):
        batch_data = batch.to_dict('records')
        salvar_dados(batch_data, token)

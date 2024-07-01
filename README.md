# currency-converter
Esta aplicación presenta una implementación de la [Exchange Rate API](https://www.exchangerate-api.com/). Ofrece un intuitivo servicio de conversión de monedas desde la consola.

> [!NOTE]
> Este es un challenger propuesto por Alura Latam - Oracle Next Education, diseñado para poner en práctica los conceptos de Java orientado a objetos (POO) y consumo de APIs.

## ✨ Caracteristicas
* Interfaz de usuario amigable vía consola.
* Soporte para conversiones usando el estándar internacional de divisas ISO 4217.
* Historial de conversiones realizadas.
 
## ⚙ Instalación
`Lenguaje` [Java OpenJDK 17 o posterior](https://openjdk.org/) 
`Dependencias`
[Gson](https://mvnrepository.com/artifact/com.google.code.gson/gson) • [Dotenv Java](https://mvnrepository.com/artifact/io.github.cdimascio/dotenv-java)
  

Clona el repositorio usando:
```bash
$ git clone https://github.com/queined/currency-converter.git
```

Obtén una API key desde [API Exchange Rate](https://www.exchangerate-api.com/). Luego, crea un archivo llamado `.env` en tu espacio de trabajo y añade la siguiente línea:
```bash
API_KEY=TU_API_KEY
```
Remplaza "TU_API_KEY" por la API key proporcionada.

## 💰 Ejecución

Abre VS Code y ejecuta App.java. En la consola, se abrirá una interfaz intuitiva para la conversión de monedas.

<p align="center">
    <img src="https://i.postimg.cc/brDs2GnT/currency.gif" width="350">
</p>

Si deseas convertir otra moneda, simplemente especifica su código de divisa en formato [ISO 4217](https://es.wikipedia.org/wiki/ISO_4217). 

<p align="center">
    <img src="https://i.postimg.cc/gjH2f1fC/code.gif" width="350">
</p>

Puedes ver el historial con la fecha de las conversiones que realizaste. No te preocupes si cometes errores al digitar, la app posee un buen manejo de ellos.


## 📜 Licencia
Este proyecto tiene la licencia MIT; consulte el archivo de [LICENSE](https://github.com/queined/currency-converter/blob/main/LICENSE) para obtener más detalles.
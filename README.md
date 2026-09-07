## Descripción

**ScanApp** es una aplicación Android orientada a entornos de supermercado/POS para consultar y actualizar información de productos a partir de códigos de barras.

La app permite:
- Configurar y guardar la IP del servidor local.
- Buscar productos por código de barras (EAN-12/EAN-13).
- Consultar detalles del producto desde una API REST (`GET /producto/{codigo}`).
- Actualizar el precio unitario del producto (`PATCH /producto/{codigo}`).

Está desarrollada en **Kotlin** con **Jetpack Compose** y **Navigation Compose**, usa **Ktor** para red, **DataStore** para persistencia local de configuración, e incluye integración con **CameraX + ML Kit** para escaneo por cámara.
## Vista Previa

### Ícono

<img src="https://live.staticflickr.com/65535/55064719972_f7714f7f01_t.jpg" width="86" height="99" alt="icono"/>

### Configuración IP
<img src="https://live.staticflickr.com/65535/55065805263_3b831bc5c6.jpg" width="375" height="500" alt="config"/>

### Buscador vía BarCode
<img src="https://live.staticflickr.com/65535/55065618826_e8231c3805_n.jpg" width="320" height="229" alt="search"/>

### Resultado
<img src="https://live.staticflickr.com/65535/55065870234_53357fc9c1.jpg" width="316" height="500" alt="resultado"/>

function cargarModulo(ruta) {
    fetch(ruta)
        .then(res => res.text())
        .then(html => {
            document.getElementById("contenido").innerHTML = html;
        })
        .catch(() => {
            document.getElementById("contenido").innerHTML =
                "<h2>Error cargando módulo</h2>";
        });
}

function imprimirContenido() {
    console.log("funcion ejecutada");
    var base64Content = document.getElementById('intReportePdf').value;
    var ventana = window.open('', 'Autorizaciones', 'width=800,height=600'); // Abre una nueva ventana o pestaña
    ventana.document.open();
    ventana.document.write(
            '<iframe width="100%" height="100%" src="data:application/pdf;base64,' + base64Content + '"></iframe>'
            );
    ventana.document.close();
    document.getElementById('intReportePdf').value = "";
    ventana.moveTo((window.screen.width - 600) / 2, (window.screen.height - 400) / 2);    
}


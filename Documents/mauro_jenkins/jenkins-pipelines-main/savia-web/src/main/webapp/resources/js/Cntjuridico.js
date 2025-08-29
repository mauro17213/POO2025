
function mostrarPdfDesdeBase64() {
    const base64 = document.getElementById('frmVisualizaDoc:infoPdf').value;

    if (!base64) {
        alert("No hay PDF disponible.");
        return;
    }

    const pdfData = atob(base64); // Decodifica el base64 a binario

    const loadingTask = pdfjsLib.getDocument({data: pdfData});

    loadingTask.promise.then(function (pdf) {
        // Cargar la primera página
        return pdf.getPage(1);
    }).then(function (page) {
        const scale = 1.0;
        const viewport = page.getViewport({scale});

        const canvas = document.getElementById('canvavisor');
        const context = canvas.getContext('2d');
        canvas.height = viewport.height;
        canvas.width = viewport.width;

        const renderContext = {
            canvasContext: context,
            viewport: viewport
        };
        page.render(renderContext);
    }).catch(function (error) {
        console.error('Error al cargar el PDF: ', error);
    });

    PF('dlgPdfWV').show();
}


function visualizarPdfFullPagina(idcontenido, idvisor) {
    const existeBase64 = document.getElementById(idcontenido);
    if(!existeBase64){
        return;
    }
        
    const base64 = document.getElementById(idcontenido).value;
    
    if (!base64) {
//        alert("No hay PDF disponible.");
        return;
    }

    const pdfData = atob(base64);
    const container = document.getElementById(idvisor);
    container.innerHTML = ''; // Limpia si ya se había abierto antes

    pdfjsLib.getDocument({data: pdfData}).promise.then(function (pdf) {
        const totalPages = pdf.numPages;

        for (let pageNum = 1; pageNum <= totalPages; pageNum++) {
            pdf.getPage(pageNum).then(function (page) {
                const scale = 1.2;
                const viewport = page.getViewport({scale});

                const canvas = document.createElement('canvas');
                const context = canvas.getContext('2d');
                canvas.style.marginBottom = '20px';
                canvas.style.border = '1px solid #b9b9b9';
                canvas.width = viewport.width;
                canvas.height = viewport.height;

                const renderContext = {
                    canvasContext: context,
                    viewport: viewport
                };

                container.appendChild(canvas);
                page.render(renderContext);
            });
        }
    });
}


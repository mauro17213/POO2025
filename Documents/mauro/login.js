const btnRegistrar = document.getElementById("registrar");
const modal = document.getElementById("modalRegistro");
const btnGuardar = document.getElementById("btnGuardar");
const btnCancelar = document.getElementById("btnCancelar");

// Abrir modal
btnRegistrar.addEventListener("click", function(e) {
    e.preventDefault();
    modal.style.display = "flex";
});

// Cerrar modal
btnCancelar.addEventListener("click", function(e) {
    e.preventDefault();
    modal.style.display = "none";
});

// Guardar usuario
btnGuardar.addEventListener("click", async function() {
    const email = document.getElementById("regEmail").value;
    const password = document.getElementById("regPassword").value;

    if (!email || !password) {
        alert("Faltan datos");
        return;
    }

    try {
        const response = await fetch("http://localhost:4000/register", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ email, password })
        });

        const data = await response.json();
        alert(data.mensaje || "Registro realizado");

        // limpiar y cerrar
        document.getElementById("regEmail").value = "";
        document.getElementById("regPassword").value = "";
        modal.style.display = "none";

    } catch (error) {
        alert("Error al registrar");
    }
});
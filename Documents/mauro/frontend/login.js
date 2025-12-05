const btnInicio = document.getElementById("Inicio");
const inputEmailLogin = document.getElementById("Email");
const inputPasswordLogin = document.getElementById("Password");


const btnRegistrar = document.getElementById("registrar");
const modalRegistro = document.getElementById("modalRegistro");
const btnGuardar = document.getElementById("btnGuardar");
const btnCancelar = document.getElementById("btnCancelar");
const inputEmailReg = document.getElementById("regEmail");
const inputPasswordReg = document.getElementById("regPassword");

const linkOlvidar = document.getElementById("olvidar");
const modalOlvidar = document.getElementById("modalOlvidar");
const inputForgotEmail = document.getElementById("forgotEmail");
const inputForgotToken = document.getElementById("forgotToken");
const newPasswordContainer = document.getElementById("newPasswordContainer");
const inputForgotNewPassword = document.getElementById("forgotNewPassword");
const btnEnviarToken = document.getElementById("btnEnviarToken");
const btnVerificarToken = document.getElementById("btnVerificarToken");
const btnGuardarNueva = document.getElementById("btnGuardarNueva");
const btnCancelarOlvidar = document.getElementById("btnCancelarOlvidar");



const formOlvidar = document.getElementById("formOlvidar");

formOlvidar.addEventListener("submit", function(e) {
    e.preventDefault(); // Nunca recargues por submit
});

btnInicio.addEventListener("click", async function(e) {
    e.preventDefault();

    const email = inputEmailLogin.value;
    const password = inputPasswordLogin.value;

    if (!email || !password) {
        alert("Faltan datos");
        return;
    }

    try {
        const respuesta = await fetch("http://localhost:4000/login", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ email, password }),
        });

        const data = await respuesta.json();
        console.log("DATA DEL BACK LOGIN:", data);

        if (data.mensaje === "Login correcto") {

            window.location.href = "./home.html";
        } else {
            alert(data.mensaje || "Error al iniciar sesión");
        }

        inputEmailLogin.value = "";
        inputPasswordLogin.value = "";
    } catch (error) {
        console.error("Error en login:", error);
        alert("Error al iniciar");
    }
});



btnRegistrar.addEventListener("click", function(e) {
    e.preventDefault();
    modalRegistro.style.display = "flex";
});


btnCancelar.addEventListener("click", function(e) {
    e.preventDefault();

    inputEmailReg.value = "";
    inputPasswordReg.value = "";


    const radios = document.querySelectorAll('input[name="tipoUsuario"]');
    radios.forEach((r) => (r.checked = false));

    modalRegistro.style.display = "none";
});

btnGuardar.addEventListener("click", async function() {
    const email = inputEmailReg.value;
    const password = inputPasswordReg.value;
    const tipoSeleccionado = document.querySelector(
        'input[name="tipoUsuario"]:checked'
    );

    if (!email || !password || !tipoSeleccionado) {
        alert("Faltan datos de registro");
        return;
    }

    const rol = tipoSeleccionado.value;

    try {
        const response = await fetch("http://localhost:4000/register", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ email, password, rol }),
        });

        const data = await response.json();
        alert(data.mensaje || "Registro realizado");


        inputEmailReg.value = "";
        inputPasswordReg.value = "";
        const radios = document.querySelectorAll('input[name="tipoUsuario"]');
        radios.forEach((r) => (r.checked = false));

        modalRegistro.style.display = "none";
    } catch (error) {
        console.error("Error al registrar:", error);
        alert("Error al registrar");
    }
});



linkOlvidar.addEventListener("click", function(e) {
    e.preventDefault();
    modalOlvidar.style.display = "flex";
    modalRegistro.style.display = "none";

});

btnCancelarOlvidar.addEventListener("click", function() {
    inputForgotEmail.value = "";
    inputForgotToken.value = "";
    inputForgotNewPassword.value = "";

    newPasswordContainer.style.display = "none";
    btnGuardarNueva.style.display = "none";

    modalOlvidar.style.display = "none";
});

btnEnviarToken.addEventListener("click", async function(e) {
    e.preventDefault();
    const email = inputForgotEmail.value;

    if (!email) {
        alert("Por favor ingresa tu correo");
        return;
    }

    try {
        const respuesta = await fetch("http://localhost:4000/generar-token", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ email }),
        });

        const data = await respuesta.json();
        console.log("DATA DEL BACK /generar-token:", data);
        alert(data.mensaje || "Si el correo existe, se generó un token (revisa consola)");
    } catch (error) {
        console.error("Error al generar token:", error);
        alert("Error al generar token");
    }
});


btnVerificarToken.addEventListener("click", async function() {
    const email = inputForgotEmail.value.trim();
    const token = inputForgotToken.value.trim();

    if (!email || !token) {
        alert("Ingresa el correo y el token");
        return;
    }

    try {
        const respuesta = await fetch("http://localhost:4000/verificar-token", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ email, token }),
        });

        const data = await respuesta.json();
        console.log("DATA DEL BACK /verificar-token:", data);

        if (data.mensaje === "token valido") {
            alert("Token válido. Ahora puedes ingresar una nueva contraseña.");

            newPasswordContainer.style.display = "block";
            btnGuardarNueva.style.display = "block";
        } else {
            alert(data.mensaje || "Token inválido");
        }
    } catch (error) {
        console.error("Error al verificar token:", error);
        alert("Error al verificar token");
    }
});


btnGuardarNueva.addEventListener("click", async function() {
    const email = inputForgotEmail.value;
    const token = inputForgotToken.value;
    const newPassword = inputForgotNewPassword.value;

    if (!email || !token || !newPassword) {
        alert("Faltan datos para cambiar la contraseña");
        return;
    }

    try {
        const respuesta = await fetch("http://localhost:4000/cambiar-contrasena", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ email, token, newPassword }),
        });

        const data = await respuesta.json();
        console.log("DATA DEL BACK /cambiar-contrasena:", data);

        if (data.mensaje === "se actualizó la contraseña") {

            alert("Contraseña actualizada correctamente");

            inputForgotEmail.value = "";
            inputForgotToken.value = "";
            inputForgotNewPassword.value = "";

            newPasswordContainer.style.display = "none";
            btnGuardarNueva.style.display = "none";
            modalOlvidar.style.display = "none";
            window.location.href = "./index.html";
        }
    } catch (error) {
        console.error("Error al cambiar contraseña:", error);
        alert("Error al cambiar contraseña");
    }
});
const gmail = document.getElementById("regEmail");
const password = document.getElementById("regPassword");
const btnGuardar = document.getElementById("btnGuardar");
const btnCancelar = document.getElementById("btnCancelar");
const modal = document.getElementById("modalRegistro");
const registro = document.getElementById("formRegistro");
const enlaceReg = document.getElementById("registrar")

const logEmail = document.getElementById("Email")
const logPassword = document.getElementById("Password")
const btnInicio = document.getElementById("btnInicio")

const btnOlvidar = document.getElementById("olvidar")
const modalOlvidar = document.getElementById("modalOlvidar")


enlaceReg.addEventListener("click", (e) => {
    e.preventDefault();
    modal.style.display = "flex";
})
console.log("entra al registrar");
btnGuardar.addEventListener("click", () => {
    console.log("entra al boton");
    const rolF = document.querySelector('input[name="tipoUsuario"]:checked');

    if (!gmail.value || !password.value || !rolF) {
        alert("Faltan datos ")
        return;
    }
    if (!gmail.value.includes("@")) {
        alert("Utiliza un correo valido, que tenga un @ ")
        return;
    }
    const rol = rolF.value;

    const usuarioObjeto = {
        gmail: gmail.value,
        password: password.value,
        rol: rol
    };
    console.log("antes del fetch");
    fetch("http://localhost:8080/login/registrar", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(usuarioObjeto)

        })
        .then(r => r.json())
        .then(ok => {
            console.log("entro al then");
            if (ok) {
                alert("Usuario registrado correctamente");
                registro.reset();
                modal.style.display = "none";
            } else {
                alert("Usuario ya registrado ");

            }
        })
        .catch(err => {
            console.error("Error al conectar con el backend:", err);
            alert("No se pudo conectar con el servidor");
        });
});
btnCancelar.addEventListener("click", (e) => {
    e.preventDefault();
    modal.style.display = "none"
});

btnInicio.addEventListener("click", () => {
    if (!logEmail || !logPassword) {
        alert("Faltan datos")
        return;
    }
    if (!logEmail.value.includes("@")) {
        alert("Utiliza un correo valido, que tenga un @ ")
        return;
    }
    const inicio = {
        logEmail: logEmail,
        logPassword: logPassword
    }
    console.log("home");
    fetch("http://localhost:8080/login/home", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(inicio)

        })
        .then(r => r.json())
        .then(ok => {
            if (ok) {
                registro.reset();
                modal.style.display = "none";
                window.location.href = "/home.html";

            } else {
                alert("El usuario no existe");

            }
        })
        .catch(err => {
            console.error("Error al conectar con el backend:", err);
            alert("No se pudo conectar con el servidor");
        });
});
btnOlvidar.addEventListener("click", (e) => {
    e.preventDefault();
    modalOlvidar.style.display = "flex"
}) btn
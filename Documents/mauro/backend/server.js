const express = require("express");
const cors = require("cors");
const sqlite3 = require("sqlite3").verbose();

const port = 4000;
const app = express();

app.use(cors());
app.use(express.json());

// 1️⃣ UNA sola base de datos con dos tablas
const bd = new sqlite3.Database("usuarios.db", (e) => {
    if (e) {
        console.error("No se pudo abrir la base de datos:", e.message);
    } else {
        console.log("✅ Base de datos abierta correctamente");
    }
});

// Tabla usuarios
bd.run(
    `
  CREATE TABLE IF NOT EXISTS usuarios (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    email TEXT UNIQUE,
    password TEXT,
    rol TEXT
  )
`,
    (e) => {
        if (e) {
            console.error("No se creó la tabla usuarios:", e.message);
        } else {
            console.log("✅ Tabla usuarios lista");
        }
    }
);
// 💌 Transporter de correo con Gmail
const transporter = nodemailer.createTransport({
    service: "gmail",
    auth: {
        user: process.env.EMAIL_USER,
        pass: process.env.EMAIL_PASS,
    },
});
// Tabla para recuperación de contraseña
bd.run(
    `
  CREATE TABLE IF NOT EXISTS usuariosRecupera (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    email TEXT,
    token TEXT,
    expiro INTEGER
  )
`,
    (e) => {
        if (e) {
            console.error("No se creó la tabla usuariosRecupera:", e.message);
        } else {
            console.log("✅ Tabla usuariosRecupera lista");
        }
    }
);

app.get("/", (req, res) => {
    res.send("Servidor funcionando correctamente ✅");
});

// 2️⃣ REGISTRO
app.post("/register", (req, res) => {
    const { email, password, rol } = req.body;

    if (!email || !password || !rol) {
        return res.json({ mensaje: "Faltan datos de registro" });
    }

    const sql = `
    INSERT INTO usuarios (email, password, rol)
    VALUES (?, ?, ?)
  `;

    bd.run(sql, [email, password, rol], function(e) {
        if (e) {
            if (e.message.includes("UNIQUE")) {
                return res.json({ mensaje: "El usuario ya existe" });
            } else {
                console.error("Error al registrar:", e.message);
                return res.json({ mensaje: "El proceso falló" });
            }
        }

        return res.json({
            mensaje: "El usuario se agregó",
            id: this.lastID,
        });
    });
});

// 3️⃣ LOGIN
app.post("/login", (req, res) => {
    const { email, password } = req.body;

    if (!email || !password) {
        return res.json({ mensaje: "Faltan datos" });
    }

    const sql = `
    SELECT * FROM usuarios
    WHERE email = ? AND password = ?
  `;

    bd.get(sql, [email, password], function(e, row) {
        if (e) {
            console.error("Error en login:", e.message);
            return res.json({ mensaje: "El proceso falló" });
        }

        if (!row) {
            return res.json({ mensaje: "no tengo credenciales en la tabla" });
        }

        return res.json({
            mensaje: "Login correcto",
            rol: row.rol,
        });
    });
});

app.post("/generar-token", (req, res) => {
    const { email } = req.body;

    if (!email) {
        return res.json({ mensaje: "Faltan datos" });
    }

    const sqlUser = `
    SELECT * FROM usuarios WHERE email = ?
  `;

    bd.get(sqlUser, [email], function(e, row) {
        if (e) {
            console.error("Error al buscar usuario:", e.message);
            return res.json({ mensaje: "El proceso falló" });
        }

        if (!row) {
            return res.json({ mensaje: "no tengo credenciales en la tabla" });
        }

        const token = Math.floor(100000 + Math.random() * 900000).toString();
        const expiro = Date.now() + 15 * 60 * 1000;


        const sqlInsert = `
      INSERT INTO usuariosRecupera (email, token, expiro)
      VALUES (?, ?, ?)
    `;

        bd.run(sqlInsert, [email, token, expiro], function(e2) {
            if (e2) {
                console.error("Error al guardar token:", e2.message);
                return res.json({ mensaje: "fallo al generar token" });
            }

            console.log("🔐 Se creó token", token, "para", email);

            return res.json({
                mensaje: "Se creó el token y se guardó en la base de datos con email, token y tiempo de expiración",
            });
        });
    });
});

app.post("/verificar-token", (req, res) => {
    const { email, token } = req.body;

    if (!email || !token) {
        return res.json({ mensaje: "Faltan datos" });
    }

    const sql = `
    SELECT * FROM usuariosRecupera
    WHERE email = ? AND token = ?
    ORDER BY id DESC
    LIMIT 1
  `;

    bd.get(sql, [email, token], function(e, row) {
        if (e) {
            console.error("Error al verificar token:", e.message);
            return res.json({ mensaje: "El proceso falló" });
        }

        if (!row) {
            return res.json({ mensaje: "token invalido" });
        }

        if (Date.now() > row.expiro) {
            return res.json({ mensaje: "el token expiro" });
        }

        return res.json({
            mensaje: "token valido",
        });
    });
});

app.post("/cambiar-contrasena", (req, res) => {
    const { email, token, newPassword } = req.body;

    if (!email || !token || !newPassword) {
        return res.json({ mensaje: "Faltan datos" });
    }

    const sqlToken = `
    SELECT * FROM usuariosRecupera
    WHERE email = ? AND token = ?
    ORDER BY id DESC
    LIMIT 1
  `;

    bd.get(sqlToken, [email, token], function(e, row) {
        if (e) {
            console.error("Error al validar token:", e.message);
            return res.json({ mensaje: "El proceso falló" });
        }

        if (!row) {
            return res.json({ mensaje: "token invalido" });
        }

        if (Date.now() > row.expiro) {
            return res.json({ mensaje: "el token expiro" });
        }

        const sqlUpdate = `
      UPDATE usuarios
      SET password = ?
      WHERE email = ?
    `;

        bd.run(sqlUpdate, [newPassword, email], function(e2) {
            if (e2) {
                console.error("Error al actualizar contraseña:", e2.message);
                return res.json({ mensaje: "El proceso fallo" });
            }

            console.log("🔁 Se actualizó la contraseña de:", email);
            return res.json({ mensaje: "contraseña" });

        });
    });
});

app.listen(port, () => {
    console.log(`✅ Servidor corriendo en http://localhost:${port}`);
});
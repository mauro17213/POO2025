const express = require("express");
const cors = require("cors");
const sqlite3 = require("sqlite3").verbose();

const port = 4000;
const app = express();


app.use(cors());
app.use(express.json());


const bd = new sqlite3.Database("usuarios.db", (e) => {
    if (e) {
        console.error("no se encontro la tabla");
    } else {
        console.log("se encontro la tabla");
    }
});

bd.run(`
    CREATE TABLE IF NOT EXISTS usuarios (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    email TEXT UNIQUE,
    password TEXT
    )
`, (e) => {
    if (e) {
        console.error("no se creo la tabla")
    } else {
        console.log("se creo la tabla");
    }
})

app.get("/", (req, res) => {
    res.send('Servidor funcionando correctamente ✅');
});

app.post("/register", (req, res) => {

    const email = req.body.email;
    const password = req.body.password;

    if (!email || !password) {
        return res.json({ mensaje: "Faltan datos" });
    }


    const sql = `
    INSERT INTO usuarios (email, password)
    VALUES (?, ?)
    `;

    bd.run(sql, [email, password], function(e) {
        if (e) {
            if (e.message.includes("UNIQUE")) {
                return res.json({ message: "El usurio ya existe" });
            } else {
                return res.json({ message: "El proceso fallo" });
            }
        }
        return res.json({
            mensaje: "El usurio yse agrego",
            id: this.lastID
        });
    })
})

app.post("/login", (req, res) => {
    const email = req.body.email;
    const password = req.body.password;

    if (!email || !password) {
        return res.status(400).json({ mensaje: "Faltan datos" });
    }
});

app.listen(port, () => {
    console.log(`✅ Servidor corriendo en http://localhost:${port}`);

})
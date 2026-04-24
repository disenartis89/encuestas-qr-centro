# Guía práctica de Git desde cero

**Para Raquel · Proyecto EncuestasQR · Nivel: 0**

Vas a aprender Git haciendo, no leyendo teoría aburrida. Al final de esta guía sabrás crear un repositorio, subirlo a GitHub, crear ramas, abrir Pull Requests, resolver conflictos, comparar cambios y revertir cosas.

Todo está pensado para que lo ejecutes tú, paso a paso. Si algo no sale, lee el error, respira, y revisa la sección correspondiente.

---

## Índice

1. Conceptos que necesitas entender antes de tocar nada
2. Instalación y configuración inicial (una sola vez)
3. Práctica 1 · Tu primer repositorio local
4. Práctica 2 · Subirlo a tu GitHub
5. Práctica 3 · Ramas: main, develop y feature
6. Práctica 4 · Tu primer Pull Request
7. Práctica 5 · Simular un compañero y provocar un conflicto
8. Práctica 6 · Revisar, comparar y deshacer
9. Git en IntelliJ (atajos y menús equivalentes)
10. Chuleta rápida de comandos

---

## 1. Conceptos que necesitas entender antes de tocar nada

Lee esto despacio. Cinco minutos ahora te ahorran horas de lío después.

**Git** es un programa que guarda fotos (snapshots) del estado de tu proyecto. Cada foto se llama **commit**. Git vive en tu ordenador.

**GitHub** es una página web donde se pueden alojar repositorios de Git. Piensa en Git como Word y en GitHub como Google Drive: son cosas distintas, pero se complementan.

**Repositorio** (o "repo") es una carpeta bajo control de Git. Tiene una carpeta oculta `.git` dentro que guarda todo el historial.

**Commit** es una foto del proyecto en un momento concreto, con un mensaje que explica qué cambió. Los commits forman una línea de tiempo.

**Rama** (*branch*) es una línea de trabajo paralela. Imagina que el proyecto es un río: la rama principal es `main`. Si quieres probar una nueva función sin romper nada, creas una rama aparte (`feature/qr`), haces cambios ahí, y cuando funciona la fusionas de vuelta a `main`.

**Merge** (fusión) es juntar el trabajo de una rama con otro. Ejemplo: terminas `feature/qr`, la mezclas con `develop`, y los cambios del QR aparecen en `develop`.

**Conflicto** pasa cuando dos ramas han modificado la misma línea del mismo archivo. Git no sabe cuál debe quedarse y te pregunta a ti. Es normal, no es un error tuyo.

**Pull Request** (PR) es una propuesta formal en GitHub para fusionar una rama en otra. Tus compañeros pueden leer los cambios, comentar, pedir modificaciones y aprobarlo antes de que se fusione. Es el punto donde ocurre la revisión de código.

**Estructura típica de ramas en un equipo (Git Flow simplificado):**

- `main` → solo contiene código listo para producción. Nadie trabaja directo aquí.
- `develop` → rama de integración. Aquí se van juntando las funcionalidades cuando están listas.
- `feature/xxx` → una por cada funcionalidad nueva. Cada compañero crea la suya. Cuando está lista, abre un PR hacia `develop`.

**Flujo típico en tu proyecto:**

1. Partes de `develop` actualizada.
2. Creas `feature/registro-alumnos`.
3. Haces commits ahí.
4. Subes la rama a GitHub.
5. Abres un Pull Request de `feature/registro-alumnos` → `develop`.
6. Tu compañero la revisa, comenta, aprueba.
7. Se hace merge. Tu rama desaparece, su contenido vive ya en `develop`.

---

## 2. Instalación y configuración inicial (una sola vez)

### 2.1 Instalar Git

- Windows: descarga Git desde https://git-scm.com/download/win y pulsa siguiente-siguiente. Deja las opciones por defecto.
- Mac: abre la Terminal y escribe `git --version`. Si no está, te saldrá un asistente para instalar las herramientas de Xcode.
- Linux: `sudo apt install git` (Ubuntu/Debian).

Comprueba que funciona abriendo una terminal y escribiendo:

```bash
git --version
```

Debe responder algo como `git version 2.45.0`.

### 2.2 Presentarte a Git

Git necesita saber quién eres para firmar tus commits. Escribe (pon tu email **real** de GitHub):

```bash
git config --global user.name "Raquel"
git config --global user.email "rcf0012@alu.medac.es"
git config --global init.defaultBranch main
```

### 2.3 Crear cuenta y token en GitHub

Si ya tienes cuenta, sáltate este paso.

1. Entra en https://github.com y regístrate con tu email.
2. Para poder subir código desde tu ordenador necesitas un **token personal** (GitHub ya no acepta la contraseña normal desde 2021). Ve a:
   `Settings` → `Developer settings` → `Personal access tokens` → `Tokens (classic)` → `Generate new token (classic)`.
3. Dale un nombre ("mi portátil"), marca la casilla **repo**, y genera el token. **Copia el token y guárdalo en un sitio seguro**, solo se ve una vez.

Cuando hagas `git push` por primera vez, te pedirá usuario y contraseña. En "contraseña" pegas el token.

---

## 3. Práctica 1 · Tu primer repositorio local

Objetivo: convertir la carpeta del proyecto en un repositorio Git, hacer tu primer commit y ver el historial.

### Paso a paso

Abre una terminal (o en IntelliJ, abajo del todo tienes una pestaña "Terminal") y entra en la carpeta del proyecto:

```bash
cd ruta/hasta/encuestas-qr-centro
```

Inicializa el repositorio:

```bash
git init
```

Esto crea una carpeta oculta `.git` dentro. Ya es un repositorio, pero todavía no hay commits.

Mira el estado:

```bash
git status
```

Verás todos los archivos en rojo como "untracked" (Git todavía no los sigue). Añádelos todos a la **zona de staging** (los cambios que van a entrar en el próximo commit):

```bash
git add .
```

El punto significa "todo". Ahora:

```bash
git status
```

Verás los archivos en verde. Están preparados para el commit. Hacemos el commit:

```bash
git commit -m "Primer commit: estructura inicial del proyecto"
```

El `-m` es el mensaje. El mensaje tiene que explicar qué hace el cambio, no cómo. Ejemplo bueno: "añade validación de email en registro". Ejemplo malo: "cambios".

Mira tu historial:

```bash
git log
```

Verás tu commit con un código largo (el *hash*). Pulsa `q` para salir del historial.

### Lo que acabas de hacer

Tu proyecto ahora tiene una "foto" guardada. Aunque borres un archivo y luego te arrepientas, puedes volver a este punto. Esto ya es mucho.

---

## 4. Práctica 2 · Subirlo a tu GitHub

Objetivo: que tu repo local tenga un "gemelo" en GitHub para poder compartirlo con compañeros.

### Paso a paso

1. Entra en https://github.com, pulsa el **+** arriba a la derecha → `New repository`.
2. Nombre: `encuestas-qr-centro`. Déjalo **público**. **NO** marques nada de "Initialize with README" (ya lo tenemos local).
3. Pulsa "Create repository". GitHub te mostrará una pantalla con instrucciones. Las que te interesan son:

En tu terminal local:

```bash
git remote add origin https://github.com/TU-USUARIO/encuestas-qr-centro.git
git branch -M main
git push -u origin main
```

- `remote add origin`: le dices a Git "hay un repositorio gemelo en esta URL, lo llamaré 'origin'".
- `branch -M main`: te aseguras de que tu rama se llama `main` (y no `master`, que es el nombre antiguo).
- `push -u origin main`: sube tu rama `main` al remoto. El `-u` guarda la conexión para que la próxima vez solo tengas que escribir `git push`.

Te pedirá usuario (el de GitHub) y contraseña (aquí pegas el **token**, no tu contraseña).

Refresca la página de GitHub. Tus archivos están ahí.

### Conceptos nuevos

- **origin**: el nombre que le pones al repositorio remoto. Por convención se llama así.
- **push**: subir. `git push` = sube mis commits al remoto.
- **pull**: bajar. `git pull` = trae los commits del remoto a mi local. Lo usarás cuando un compañero haya subido algo y tú quieras esos cambios.

---

## 5. Práctica 3 · Ramas: main, develop y feature

Objetivo: montar la estructura de ramas que usarás con tu equipo real.

### Paso a paso

Crea la rama `develop` a partir de `main`:

```bash
git checkout -b develop
```

`checkout -b` = crea una rama y muévete a ella. Ahora estás trabajando en `develop`.

Comprueba en qué rama estás:

```bash
git branch
```

Verás un asterisco (`*`) en la rama activa.

Sube `develop` al remoto:

```bash
git push -u origin develop
```

Ahora simula que vas a implementar el QR. Crea una rama feature:

```bash
git checkout -b feature/qr
```

Crea un archivo nuevo dentro de `backend/` llamado `Qr.java` con este contenido (en IntelliJ: clic derecho sobre la carpeta `backend` → New → Java Class → escribe `Qr`):

```java
// backend/Qr.java
// Generación de códigos QR para encuestas

package com.centro.encuestas;

public class Qr {

    public String generarQr(int idEncuesta) {
        // Devuelve una URL fake con el QR de la encuesta
        return "https://centro.edu/encuesta/" + idEncuesta + "/qr";
    }
}
```

Guárdalo. Vuelve a la terminal:

```bash
git status
```

Verás `backend/Qr.java` como untracked. Añádelo y haz commit:

```bash
git add backend/Qr.java
git commit -m "Añade generador básico de QR para encuestas"
```

Sube la rama al remoto:

```bash
git push -u origin feature/qr
```

### Cambiar entre ramas

Vuelve a `develop`:

```bash
git checkout develop
```

Mira la carpeta `backend/`: **el archivo `Qr.java` ha desaparecido**. No lo ha borrado, simplemente no existe en esta rama. Es una de las cosas más potentes de Git: cada rama es un universo paralelo.

Vuelve a `feature/qr`:

```bash
git checkout feature/qr
```

El archivo reaparece. Magia.

---

## 6. Práctica 4 · Tu primer Pull Request

Objetivo: abrir un PR en GitHub para pedir que tu `feature/qr` se fusione en `develop`, como harías con tus compañeros.

### Paso a paso

1. Ve a tu repositorio en GitHub. Arriba te aparecerá un aviso amarillo: "feature/qr had recent pushes · Compare & pull request". Púlsalo.
2. Si no aparece: pestaña `Pull requests` → `New pull request`. Selecciona `base: develop` y `compare: feature/qr`.
3. Rellena el formulario:
   - **Título**: "Añade generador de QR para encuestas"
   - **Descripción**: explica qué hace, qué falta, a quién avisas. Ejemplo:
     ```
     ## Qué hace
     Añade el archivo backend/Qr.java con la función generar_qr().

     ## Falta
     - Validar que la encuesta existe antes de generar el QR.
     - Tests.

     ## Revisores
     @compañero1
     ```
4. Pulsa `Create pull request`.

### Qué ves ahora en GitHub

- **Conversation**: los comentarios del PR.
- **Commits**: los commits que van a entrar si se hace merge.
- **Files changed**: la comparación línea a línea (rojo = eliminado, verde = añadido). **Esta es la pantalla que usarás para revisar código de tus compañeros.** Puedes pulsar el `+` al lado de cualquier línea para dejar un comentario.

### Fusionar el PR

Como estás sola, aprueba tú misma. En una situación real, esto lo haría un compañero:

1. Pulsa el botón verde `Merge pull request`.
2. `Confirm merge`.
3. Pulsa `Delete branch` para borrar `feature/qr` del remoto (ya está integrada, no la necesitas).

Vuelve a tu terminal y sincroniza:

```bash
git checkout develop
git pull
```

Ahora `develop` local tiene el archivo `Qr.java`. Puedes borrar la rama local también (ya no hace falta):

```bash
git branch -d feature/qr
```

---

## 7. Práctica 5 · Simular un compañero y provocar un conflicto

Objetivo: aprender a resolver conflictos, que es lo que más asusta y menos razón tiene a asustar.

### El escenario

Imagina que tu compañero Pablo ha modificado `backend/Usuarios.java` mientras tú también lo tocabas. Vamos a simularlo haciendo dos ramas que tocan la misma línea.

### Paso a paso

Asegúrate de estar en `develop` y actualizada:

```bash
git checkout develop
git pull
```

**Tu rama** (simulando tu trabajo): crea `feature/validacion-nombre`:

```bash
git checkout -b feature/validacion-nombre
```

Abre `backend/Usuarios.java`. Justo encima del método `registrarAlumno`, añade una línea de comentario. Sustituye:

```java
    public Usuario registrarAlumno(String nombre, String curso) {
```

por:

```java
    // Registra un alumno validando que el nombre no esté vacío.
    public Usuario registrarAlumno(String nombre, String curso) {
```

Guarda, y haz commit:

```bash
git add backend/Usuarios.java
git commit -m "Añade validación de nombre en registro de alumnos"
```

Vuelve a `develop`:

```bash
git checkout develop
```

**La rama de Pablo** (simulando su trabajo en paralelo): crea `feature/curso-obligatorio`:

```bash
git checkout -b feature/curso-obligatorio
```

Abre `backend/Usuarios.java`. En la misma posición (justo encima de `registrarAlumno`), añade este comentario en vez del anterior:

```java
    // Registra un alumno asegurando que el curso es obligatorio.
    public Usuario registrarAlumno(String nombre, String curso) {
```

Guarda:

```bash
git add backend/Usuarios.java
git commit -m "Exige curso en registro de alumnos"
```

Fusiona Pablo en develop (como si su PR hubiera sido aprobado):

```bash
git checkout develop
git merge feature/curso-obligatorio
```

Ahora intenta fusionar tu rama:

```bash
git merge feature/validacion-nombre
```

**BOOM**. Git te dice:

```
CONFLICT (content): Merge conflict in backend/Usuarios.java
Automatic merge failed; fix conflicts and then commit the result.
```

No pasa nada. Abre `backend/Usuarios.java` (en IntelliJ ya te lo marca en rojo). Verás algo como:

```java
<<<<<<< HEAD
    // Registra un alumno asegurando que el curso es obligatorio.
=======
    // Registra un alumno validando que el nombre no esté vacío.
>>>>>>> feature/validacion-nombre
    public Usuario registrarAlumno(String nombre, String curso) {
```

- Lo que está entre `<<<<<<<` y `=======` es lo que había en `develop` (la versión de Pablo).
- Lo que está entre `=======` y `>>>>>>>` es lo tuyo.

Tienes tres opciones: quedarte con la de Pablo, con la tuya, o juntar las dos. Elige la tercera: edita a mano para que quede así:

```java
    // Registra un alumno validando que el nombre no esté vacío y exigiendo curso.
    public Usuario registrarAlumno(String nombre, String curso) {
```

Borra todas las líneas con `<<<<<<<`, `=======` y `>>>>>>>`. Guarda.

Dile a Git que ya está arreglado:

```bash
git add backend/Usuarios.java
git commit -m "Fusiona validación de nombre y curso obligatorio"
```

Ya está. Has resuelto tu primer conflicto. No es nada más que eso: elegir qué líneas se quedan.

### En IntelliJ (atajo)

Cuando hay conflicto, IntelliJ te abre una ventana con tres paneles (izquierda = la versión de Pablo, centro = resultado final, derecha = la tuya). Marcas con las flechas qué líneas quieres del lado izquierdo o derecho, y editas el centro si necesitas. Al final pulsas "Apply".

---

## 8. Práctica 6 · Revisar, comparar y deshacer

Comandos que usarás a diario para entender qué está pasando en tu repo.

### Ver el historial

```bash
git log
```

Lista todos los commits. Muy verboso. Mejor con formato compacto:

```bash
git log --oneline --graph --all
```

- `--oneline`: un commit por línea.
- `--graph`: dibuja las ramas.
- `--all`: muestra todas las ramas, no solo la actual.

Pulsa `q` para salir.

### Ver qué ha cambiado (diff)

Cambios que NO has hecho commit todavía:

```bash
git diff
```

Cambios de un archivo concreto:

```bash
git diff backend/Usuarios.java
```

Diferencia entre dos ramas:

```bash
git diff develop..feature/qr
```

Diferencia entre dos commits (usa los hash cortos que ves en `git log`):

```bash
git diff abc1234 def5678
```

### Ver quién tocó cada línea

```bash
git blame backend/Usuarios.java
```

Te muestra, al lado de cada línea, quién la escribió y cuándo. Útil para entender el código de compañeros.

### Deshacer cosas

**1. He modificado un archivo y quiero volver a como estaba:**

```bash
git checkout -- backend/Usuarios.java
```

O en versiones nuevas de Git:

```bash
git restore backend/Usuarios.java
```

**2. He hecho `git add` pero no `git commit` y quiero sacar el archivo del staging:**

```bash
git restore --staged backend/Usuarios.java
```

**3. Tengo trabajo a medias pero tengo que cambiar de rama sin hacer commit:**

```bash
git stash        # guarda lo que tengas en un cajón
git checkout otra-rama
# ... haces lo que necesites ...
git checkout tu-rama
git stash pop    # recupera lo del cajón
```

**4. Quiero deshacer el último commit pero mantener los cambios:**

```bash
git reset --soft HEAD~1
```

Los cambios vuelven al staging, como si nunca hubieras hecho commit.

**5. Quiero deshacer un commit que ya subí a GitHub (sin reescribir historia):**

```bash
git revert HASH_DEL_COMMIT
```

Esto crea un commit nuevo que anula el anterior. Seguro para ramas compartidas.

⚠️ **`git reset --hard` borra cambios sin preguntar.** No lo uses a la ligera.

---

## 9. Git en IntelliJ (atajos y menús equivalentes)

IntelliJ tiene Git integrado. Cada comando tiene su equivalente visual:

| Comando | IntelliJ |
|---|---|
| `git status` | Pestaña `Git` (abajo) → `Local Changes` |
| `git add` + `git commit` | `Ctrl+K` (Cmd+K en Mac). Se abre una ventana, escribes el mensaje y pulsas Commit. |
| `git push` | `Ctrl+Shift+K` (Cmd+Shift+K en Mac) |
| `git pull` | Menú `Git` → `Pull` |
| `git checkout rama` | Abajo a la derecha, clic en el nombre de la rama actual → elige la que quieras. |
| `git checkout -b feature/x` | Mismo menú → `New Branch`. |
| `git log` | Pestaña `Git` → `Log`. Muy visual, muestra el árbol. |
| `git diff` | Clic derecho en un archivo modificado → `Show Diff` (o `Ctrl+D`). |
| Resolver conflicto | Te avisa automáticamente al hacer merge. Clic en `Resolve` → abre el editor de 3 paneles. |

**Consejo:** cuando estés aprendiendo, alterna terminal e IntelliJ. Ejecuta un comando en terminal, mira qué cambió en IntelliJ. Así atas los dos mundos.

---

## 10. Chuleta rápida de comandos

```bash
# --- Configuración inicial ---
git config --global user.name "Nombre"
git config --global user.email "email"

# --- Empezar ---
git init                      # crea un repo local
git clone URL                 # descarga un repo de GitHub

# --- Día a día ---
git status                    # qué está cambiado/sin añadir
git add archivo               # prepara un archivo para commit
git add .                     # prepara todo
git commit -m "mensaje"       # guarda el commit
git push                      # sube al remoto
git pull                      # baja del remoto

# --- Ramas ---
git branch                    # lista ramas locales
git branch -a                 # incluye remotas
git checkout nombre           # cambia a esa rama
git checkout -b nombre        # crea y cambia
git branch -d nombre          # borra rama local (si ya está mergeada)
git push origin nombre        # sube la rama al remoto
git push origin --delete nom  # borra la rama del remoto

# --- Merge ---
git merge otra-rama           # trae otra-rama a la actual
git merge --abort             # cancela un merge en curso

# --- Historial y diffs ---
git log --oneline --graph --all
git diff                      # cambios sin stagear
git diff --staged             # cambios staged
git diff rama1..rama2
git blame archivo             # quién tocó cada línea

# --- Deshacer ---
git restore archivo           # descarta cambios sin commit
git restore --staged archivo  # sacar del staging
git reset --soft HEAD~1       # deshace último commit, mantiene cambios
git revert HASH               # crea commit que deshace otro
git stash                     # guarda trabajo temporal
git stash pop                 # lo recupera

# --- Sincronizar con el equipo ---
git fetch                     # baja info del remoto sin mergear
git pull                      # fetch + merge
```

---

## Y ahora ¿qué hago con mi proyecto real?

1. **Nunca hagas commit directo a `main` o `develop`.** Siempre crea una `feature/xxx` primero.
2. **Actualiza antes de empezar**: `git checkout develop` → `git pull`. Evitas muchos conflictos.
3. **Commits pequeños y frecuentes**. Mejor 10 commits pequeños con mensajes claros que uno gigante de "cambios varios".
4. **Antes de abrir un PR**, verifica en GitHub la pestaña `Files changed`: mira tú misma lo que envías. Si ves algo raro, mejor arreglarlo antes que te lo diga un compañero.
5. **Al revisar PRs de otros**, entra en `Files changed`, lee tranquila, deja comentarios concretos (no "esto está mal", sino "aquí el nombre de la variable no es descriptivo, ¿qué tal `idAlumno`?").
6. **Si te pierdes, respira**: tu código está a salvo en los commits anteriores. Nada de lo que hagas en una rama tira el trabajo de los demás.

Suerte. Todo esto que ahora parece hechizo, en dos semanas lo harás sin mirar.

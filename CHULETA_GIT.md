# Chuleta rápida de Git

Comandos que usarás el 95% del tiempo, agrupados por situación. Pensado para tenerla abierta al lado mientras trabajas.

---

## Configuración inicial (solo una vez)

```bash
git config --global user.name "Tu nombre"
git config --global user.email "tu@email.com"
git config --global init.defaultBranch main
git config --global core.editor "nano"   # o "code --wait" si usas VSCode
```

---

## Empezar un repositorio

```bash
git init                      # convierte la carpeta actual en repo
git clone URL                 # descarga un repo ya existente de GitHub
git clone URL carpeta         # clónalo con otro nombre de carpeta
```

---

## El día a día (ciclo básico)

```bash
git status                    # ¿qué he cambiado?
git add archivo               # prepara UN archivo para commit
git add .                     # prepara TODO lo cambiado
git add -p                    # revisa trozo a trozo antes de añadir (recomendado)
git commit -m "mensaje"       # crea el commit
git push                      # sube al remoto
git pull                      # baja los cambios de los compañeros
```

Regla de oro: antes de empezar a trabajar, siempre `git pull` en la rama. Evita la mitad de los conflictos.

---

## Ramas

```bash
git branch                    # lista ramas locales (la actual con *)
git branch -a                 # incluye remotas
git checkout nombre           # cámbiate a esa rama
git checkout -b nombre        # CREA y te cambias a ella
git switch nombre             # versión moderna de checkout (solo cambiar)
git switch -c nombre          # versión moderna de crear + cambiar

git branch -d nombre          # borra rama local (si ya está mergeada)
git branch -D nombre          # borra a la fuerza (cuidado)
git push origin nombre        # sube la rama al remoto
git push origin --delete nom  # borra la rama del remoto
```

**Convención de nombres para ramas:**

- `feature/registro-alumnos` — funcionalidad nueva
- `fix/login-bug` — arreglo de bug
- `hotfix/error-qr-produccion` — urgencia en producción
- `refactor/limpieza-usuarios` — reorganización de código

---

## Merge y fusión

```bash
git merge otra-rama           # trae los cambios de otra-rama a la actual
git merge --abort             # cancela un merge que salió mal
git merge --no-ff rama        # fuerza un commit de merge (historial más claro)
```

**Flujo típico:**

```bash
git checkout develop
git pull
git merge feature/qr          # si todo OK, se fusiona sola
git push
```

---

## Resolver conflictos

Cuando sale `CONFLICT`:

1. `git status` → te dice qué archivos tienen conflicto.
2. Abre esos archivos. Verás bloques así:
   ```
   <<<<<<< HEAD
   línea que había en tu rama actual
   =======
   línea que viene de la otra rama
   >>>>>>> otra-rama
   ```
3. Edita a mano dejando solo lo que debe quedar (o mezcla ambas versiones). Borra los `<<<`, `===`, `>>>`.
4. ```bash
   git add archivo
   git commit                # sin -m, te abre un editor con mensaje por defecto
   ```

Si te arrepientes a mitad:

```bash
git merge --abort             # vuelve al estado anterior al merge
```

---

## Ver qué ha cambiado (diff)

```bash
git diff                      # cambios que NO has hecho add todavía
git diff --staged             # cambios YA añadidos pendientes de commit
git diff archivo              # solo ese archivo
git diff rama1..rama2         # diferencia entre dos ramas
git diff HEAD~1 HEAD          # último commit vs anterior
git diff abc1234 def5678      # entre dos commits (usa hashes de git log)
```

---

## Historial

```bash
git log                       # historial completo (q para salir)
git log --oneline             # una línea por commit
git log --oneline --graph --all   # muestra TODAS las ramas con árbol
git log -p archivo            # historial de un archivo con cambios
git log --author="Pablo"      # commits de una persona
git log --since="1 week"      # commits de la última semana

git show HASH                 # detalle de un commit concreto
git blame archivo             # quién tocó cada línea
```

---

## Deshacer (sin dramas)

| Quiero... | Comando |
|---|---|
| Descartar cambios sin commit de UN archivo | `git restore archivo` |
| Descartar TODOS los cambios sin commit | `git restore .` |
| Sacar un archivo del staging (hecho `add` sin querer) | `git restore --staged archivo` |
| Deshacer el último commit pero mantener los cambios | `git reset --soft HEAD~1` |
| Deshacer un commit ya subido al remoto (seguro) | `git revert HASH` |
| Cambiar el mensaje del último commit (no subido) | `git commit --amend -m "nuevo mensaje"` |
| Guardar cambios a medias para cambiar de rama | `git stash` |
| Recuperar lo guardado con stash | `git stash pop` |
| Ver la lista de stashes | `git stash list` |

⚠️ **`git reset --hard` borra cambios sin preguntar.** Úsalo solo si estás segura de tirarlo todo.

---

## Sincronizar con el equipo

```bash
git fetch                     # baja info del remoto sin fusionar
git pull                      # fetch + merge en una orden
git pull --rebase             # pull que no crea merge commit (historial más limpio)
git push                      # sube tus commits al remoto
git push -u origin rama       # primera vez que subes una rama nueva
```

**Si al hacer push te dice "rejected":**

```bash
git pull --rebase             # integra lo que hay en remoto con lo tuyo encima
git push                      # vuelve a intentarlo
```

---

## Pull Requests (GitHub)

No son un comando de Git, se hacen en la web de GitHub:

1. Sube tu rama: `git push -u origin feature/xxx`
2. En GitHub: pestaña `Pull requests` → `New pull request`.
3. `base: develop` · `compare: feature/xxx`.
4. Título claro + descripción.
5. Asigna revisores.

**Para revisar un PR de un compañero:** pestaña `Files changed`. Pulsa el `+` al lado de una línea para dejar comentario. Botón `Review changes` arriba → `Approve` / `Request changes` / `Comment`.

**Para probar la rama de un compañero en local:**

```bash
git fetch
git checkout nombre-de-la-rama
```

---

## Situaciones típicas que te vas a encontrar

### "Me he liado y quiero empezar de cero este archivo"
```bash
git restore archivo
```

### "Me equivoqué de rama y ya hice commits en main"
```bash
git branch feature/lo-que-toque     # guarda tus commits en una rama nueva
git reset --hard origin/main        # main vuelve a como está en remoto
git checkout feature/lo-que-toque   # sigue currando aquí
```

### "Necesito urgente lo que hizo un compañero pero tengo cosas a medias"
```bash
git stash
git pull
git stash pop
```

### "Quiero ver qué ha cambiado en la rama de Pablo antes de hacer merge"
```bash
git fetch
git diff develop..origin/feature/pablo
```

### "El merge está saliendo regular, me rindo"
```bash
git merge --abort
```

### "Subí algo con datos sensibles por error"
Avisa al equipo inmediatamente. Hay que rotar el secreto y reescribir historia (`git filter-repo` o BFG). Esto ya es cosa de ayuda de alguien con experiencia.

---

## Equivalencias en IntelliJ

| Terminal | IntelliJ |
|---|---|
| `git status` | Pestaña `Git` (abajo) → `Local Changes` |
| `git add` + `git commit` | `Ctrl+K` (Cmd+K en Mac) |
| `git push` | `Ctrl+Shift+K` (Cmd+Shift+K en Mac) |
| `git pull` | Menú `Git` → `Pull` |
| `git checkout rama` | Abajo a la derecha: clic en el nombre de la rama |
| `git checkout -b rama` | Mismo menú → `New Branch` |
| `git log` | Pestaña `Git` → `Log` (muestra árbol visual) |
| `git diff archivo` | Clic derecho en el archivo → `Show Diff` (`Ctrl+D`) |
| `git stash` / `stash pop` | Menú `Git` → `Uncommitted Changes` → `Stash` / `Unstash` |
| Resolver conflicto | Te aparece ventana automática → editor de 3 paneles |

---

## Las tres reglas que nunca debes romper

1. **Nunca hacer commit directo en `main` o `develop`.** Siempre en una rama `feature/xxx`.
2. **Antes de empezar a trabajar**: `git checkout develop` + `git pull`. Siempre.
3. **Commits pequeños y con mensaje claro**. Si tu mensaje dice "cambios varios", tienes que dividirlo.

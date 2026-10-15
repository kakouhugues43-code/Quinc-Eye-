# Quinc-Eye

Application de gestion de stock, ventes et comptabilité pour quincaillerie.

Ce dépôt contient le scaffold initial d'une application Android (APK) en Kotlin.

But: le dépôt d'origine ne contenait pas de fichiers. J'ai ajouté une structure minimale de projet Android. Pour compiler et générer un APK localement, ouvrez le projet dans Android Studio et laissez-le télécharger le Gradle wrapper et le SDK, ou ajoutez le Gradle wrapper (`gradle wrapper`) sur votre machine.

Principaux fichiers ajoutés :
- `app/` : module Android contenant MainActivity et ressources
- `build.gradle`, `settings.gradle` : configuration Gradle minimale
- `README.md` : ce fichier
- `.gitignore` : fichiers à ignorer

Si tu veux que je crée le Gradle wrapper, ajoute une CI pour builder l'APK automatiquement, ou modifie le package/app name, dis-moi et je m'en occupe.

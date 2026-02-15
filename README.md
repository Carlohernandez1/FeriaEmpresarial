# Feria Empresarial (Java - Consola)
Este repositorio contiene el desarrollo de la guia 1. Activdad 1 y 2

Actividad 1. Diagramas de clases en UML - Desarrollo de un diagrama de clases UML para la «Serie de Ejercicios de Modelado No. 1» (individual).
Actividad 2. Programación Orientada por Objetos básica en Java- Proyecto “Feria empresarial” desarrollado en el lenguaje de programación Java. Este proyecto, desarrollado de manera individual por el estudiante, debe estar guardado en un repositorio Git compartido y construido en algún IDE tradicional de Java (Eclipse, IntelliJ o NetBeans) (individual).

Aplicación de consola en Java para gestionar una feria empresarial:

- Registro/edición/eliminación de **Empresas** y **Visitantes**
- Gestión de **Stands** (crear, asignar empresa, liberar, listar disponibles/ocupados)
- **Interacciones**: los visitantes dejan **comentarios** y **calificaciones (1–5)** a stands
- **Reportes**:
  1. Empresas y sus stands asignados
  2. Visitantes y los stands visitados
  3. Calificación promedio por stand
 
## 📁 Estructura del código
src/com/feria/ ├─ gestion/ │ └─ FeriaEmpresarial.java # Lógica de negocio y reportes ├─ main/ │ └─ Main.java # Menú de consola └─ modelo/ ├─ Comentario.java ├─ Empresa.java ├─ Stand.java ├─ TamanoStand.java ├─ Validaciones.java ├─ Visita.java └─ Visitante.java

## ▶️ Ejecución
1. Abrir el proyecto (JDK 17+).
2. Ejecutar `src/com/feria/main/Main.java` (Run 'Main.main()').
3. Navegar con el menú por consola.

## 🧪 Flujo de prueba rápido
1. Registrar 2 empresas  
2. Registrar 3 stands y asignar 2 a las empresas  
3. Registrar 2 visitantes  
4. Cada visitante visita un stand y comenta (calificación 1–5)  
5. Ver Reportes (opciones 1, 2 y 3)


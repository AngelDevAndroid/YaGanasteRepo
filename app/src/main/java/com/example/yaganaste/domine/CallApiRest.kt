package com.example.yaganaste.domine

import com.example.yaganaste.data.BancsModel
import retrofit2.Response
import retrofit2.http.GET

// Api
interface CallApiRest {

    @GET("catom/api/challenge/banks")
    suspend fun getBancs(): Response<List<BancsModel>>
}

/*
Objetivos de la prueba
Esta parte de la entrevista es práctica, desarrollar una aplicación Android que consuma datos.
de la API de O+ Banks, demostrando su capacidad para trabajar con lo mencionado
tecnologías y metodologías.
Desafíos
• Mostrar una lista de bancos.
• Al seleccionar un banco, mostrar una nueva pantalla con detalles más completos del mismo.
• Implementar una función de búsqueda que permita filtrar bancos por nombre.
• Permitir a los usuarios marcar bancos como favoritos y guardar esta información localmente con ROOM.
Requerimientos técnicos
• Utilice componentes Jetpack para la arquitectura de la aplicación y Compose para el usuario
interfaz. La aplicación debe desarrollarse utilizando Kotlin.
• La estructura de la aplicación debe seguir el patrón MVVM y estar organizada.
según los principios de la Arquitectura Limpia.
• Esto incluye separación de responsabilidades, uso de casos de uso y una distinción clara.
entre capas de datos, dominio y presentación.
• Utilice ROOM para almacenar en caché localmente información relevante, como los bancos favoritos del usuario.
detalles.
• Demostrar el uso efectivo de patrones de diseño adicionales que facilitan el código.
desarrollo y mantenimiento.
• El código debe estar versionado con Git, organizado con la metodología GitFlow. El
La calidad del historial de confirmaciones y la organización de ramas para funciones, etc.,
ser valorado.
• La aplicación debe consumir la API REST de O+ Banks para obtener y mostrar la
datos. Manejo de errores y serialización JSON.

Entregables
Código fuente de la aplicación en un repositorio Git (GitHub, GitLab, etc.)
Breve documentación que describe la solución implementada, las decisiones de diseño tomadas,
y opcionalmente cómo se organizaría el trabajo siguiendo Scrum.
Instrucciones para construir y ejecutar la aplicación y las pruebas.

*/

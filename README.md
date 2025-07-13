# proyecto_final
Gestor de torneo de ajedrez  
¿De qué trata? 
Tomarás el rol de organizador de un torneo de ajedrez, donde puedes determinar la modalidad de juego (Sistema Suizo, Eliminación directa, Todos contra todos), si es por equipos o individual, los tiempos 
de cada partida, así como también aceptar a los participantes que disputarán. Una vez inicies el torneo deberás encargarte de ordenar los enfrentamientos e iniciar las rondas, podrás ver los resultados de cada 
partida y el avance de los participantes. Como el torneo está simulado de forma realista, es probable encontrar empates, por ende deberás mandar a desempatar para ver los ganadores. 

Decisiones tomadas y patrones de diseño   
Para darle realismo al torneo, hicimos un generador de jugadores logrando variedad a la hora de escoger los participantes e implementamos una clase (ResultadoJuego) de Probability for the outcome of a chess game based on rating de Otto Milvang, pero con un ligero cambio, el random sin semilla, así cada resultado puede ser distinto a pesar de que los jugadores sean los mismos. En la lógica buscamos usar interfaces dentro de lo posible, pensando en el principio SOLID de inversión de dependencias, como la interfaz Participante, el comportamiento de equipo y jugador invididual es practicamente el mismo para muchos metodos del torneo, por ende decidimos trabajar en su mayoria con Participantes que con las implementaciones concretas. Para los enfrentamientos decidimos usar el patron Factory, que dependiendo del tipo de participantes nos crea el enfrentamiento concreto cuando se inician las rondas en torneo, donde enfrentamiento es una interfaz. También, la modalidad de juego es una interfaz. Aplicando el patrón de diseño Strategy, logramos que torneo ordenara a los participantes, ya sea para mostrarlos u ordenar los enfrentamientos, de acuerdo a la forma que cada modalidad designa. Respecto a la visual, usamos Observer para bloquear/desbloquear los botones en funcion de si existen partidas en juego o no y Singleton en los paneles de ver estado y ver historial. 

Problemas encontrados y sus soluciones  

1. EMPATES: En las modalidades todos contra todos y torneo suizo era muy común el desempate al ser por puntos, para desempatar en el ajedrez existen muchos criterios para desempatar que siguen un orden de prioridad, pero estos acomplejaban mucho la lógica. Entonces decidimos que si empatan dos jugadores por un lugar se cree un enfrentamiento entre ellos, como en los enfrentamientos no se puede terminar en empate ya que se generan partidas en un loop hasta salir de la situación, ya nos libramos del emapte. Pero si existe un empate entre más de dos jugadores se crea un torneo de eliminación directa. Existen muchas formas de empate en el torneo, si tomamos los puntajes posibles (arbitrarios) 4pts,2pts,1pts podriamos tener: 3 con 4pts; 2 con 4pts, 2 con 2pts; 1 con 4pts, 3 con 2 pts; etc. Determinar los lugares tras el desempate complejizó la lógica bastante. (obs: cuando se crean estos subtorneos de desempate se siguen agregando puntos por victoria, ya que se reutiliza toda la logica, sin embargo como la eliminacion directa no considera estos puntos para ganar, estos puntos al final del torneo pierden relevancia. Se aclara ya que si vemos el podio tras un desempate, es posible ver que dos jugadores tienen el mismo puntaje).
2. 

   









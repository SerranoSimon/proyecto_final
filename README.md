# proyecto_final
Grupo 5: Wladimir Placencia, Simón Serrano.  

Gestor de torneo de ajedrez  

¿De qué trata?   

Tomarás el rol de organizador de un torneo de ajedrez, donde puedes determinar la modalidad de juego (Sistema Suizo, Eliminación directa, Todos contra todos), si es por equipos o individual, los tiempos 
de cada partida, así como también aceptar a los participantes que disputarán. Una vez inicies el torneo deberás encargarte de ordenar los enfrentamientos e iniciar las rondas, podrás ver los resultados de cada 
partida y el avance de los participantes. Como el torneo está simulado de forma realista, es probable encontrar empates, por ende deberás mandar a desempatar para ver los ganadores. 

Observaciones  

1. Sobre los enfrentamientos    
   Cuando se tiene un torneo individual se crean enfrentamientos entre jugadores, los cuales consisten en 2 partidas de base (uno con blancas, el otro con negras y luego cambian), si empatan se generan partidas     de desempate de a pares, esto es que considera el cambio de lado. Si tenemos un torneo por equipos, se crean enfrentamientos entre jugadores donde es el mejor de cada equipo(respecto al ELO) vs el mejor del      otro y se sigue con ese orden. Si empatan se vuelve a disputar el mismo enfrentamiento entre equipos hasta salir.  
  
2. Sobre tiempo de las partidas   
   En el torneo se usan dos tiempos, el normal y el de desempate, en ver historial podrás ver las partidas que fueron necesarias para el enfrentamiento. Esto determina un tiempo total del enfrentamiento. A fines    de la interfaz gráfica 1 minuto equivale a 1 segundo. El tiempo de desempate SOLO se usa para las partidas de empate de enfrentamiento de jugadores, el torneo de desempate repite los misma configuracion de       tiempos del torneo original.  
  
3. Participantes fantasma   
   En los casos de tener un número de participantes impar en una ronda, se usan participantes fantasma, donde enfrentarse con ellos representa descansar. Estos jugadores los podemos detectar con un String vacío     como nombre, además que su enfrentamiento no tiene duración. Se considera que gana el participante no fantasma y se le da 1 punto (puntaje de empate).  
  
 4. Sobre ordenar enfrentamientos  
    Aclarado en la documentación de cada modalidad de juego.  
  
  

Decisiones tomadas y patrones de diseño   

Para darle realismo al torneo, hicimos un generador de jugadores logrando variedad a la hora de escoger los participantes e implementamos una clase (ResultadoJuego) de Probability for the outcome of a chess game based on rating de Otto Milvang, pero con un ligero cambio, el random sin semilla, así cada resultado puede ser distinto a pesar de que los jugadores sean los mismos. En la lógica buscamos usar interfaces dentro de lo posible, pensando en el principio SOLID de inversión de dependencias, como la interfaz Participante, el comportamiento de equipo y jugador invididual es practicamente el mismo para muchos metodos del torneo, por ende decidimos trabajar en su mayoria con Participantes que con las implementaciones concretas. Para los enfrentamientos decidimos usar el patron Factory, que dependiendo del tipo de participantes nos crea el enfrentamiento concreto cuando se inician las rondas en torneo, donde enfrentamiento es una interfaz. También, la modalidad de juego es una interfaz. Aplicando el patrón de diseño Strategy, logramos que torneo ordenara a los participantes, ya sea para mostrarlos u ordenar los enfrentamientos, de acuerdo a la forma que cada modalidad designa. Respecto a la visual, usamos Observer para bloquear/desbloquear los botones en funcion de si existen partidas en juego o no y Singleton en los paneles PanelEstadoTorneo, PanelConsola y PanelProximosEnfrentamientos.
(obs. podría mencionarse el uso de composite en equipo ya que lo forman participantes (que puede ser otro equipo) pero como no lo usamos de esta forma, no lo consideramos como un patrón de diseño en el proyecto)

Problemas encontrados y sus soluciones  

1. Empates: En las modalidades todos contra todos y torneo suizo era muy común el empate al ser por puntos, para desempatar en el ajedrez existen muchos criterios que siguen un orden de prioridad, pero estos acomplejaban mucho la lógica. Entonces decidimos que si empatan dos jugadores por un lugar se cree un enfrentamiento entre ellos, como en los enfrentamientos no se puede terminar en empate( ya que se generan partidas en un loop hasta salir de la situación), ya nos libramos de este. Pero si existe un empate entre más de dos jugadores se crea un torneo de eliminación directa. Existen muchas formas de empate en el torneo, si tomamos los puntajes posibles puntajes (arbitrarios) 4pts,2pts,1pts podriamos tener: 3 con 4pts; 2 con 4pts, 2 con 2pts; 1 con 4pts, 3 con 2 pts; etc. Determinar los lugares tras el desempate complejizó la lógica bastante. (obs: cuando se crean estos subtorneos de desempate se siguen agregando puntos por victoria, ya que se reutiliza toda la logica, sin embargo como la eliminacion directa no considera estos puntos para ganar, estos puntos al final del torneo pierden relevancia. Se aclara ya que si vemos el podio tras un desempate, es posible ver que dos jugadores tienen el mismo puntaje). No obstante, la creación del torneo para el desempate, al tener la variedad de casos, decidimos que el control de organizador se limitara a solo aceptar que se genere un desempate para poder dar los ganadores. Otro punto que vale la pena destacar es que en los casos donde se crea un torneo de desempate con participantes impar, se pueden presentar ciertas situaciones que, tal vez, se podrían considerar injustas: imaginemos que tenemos 3 participantes [A,B,C], la primera ronda sería A vs C y B descansa, si gana A sigue A vs B y C descansa (disputa tercer lugar), si gana A queda: 1er lugar A, 2do B y 3ro C, notar que tanto B como C perdieron solo una vez contra A pero queda mejor posicionado B.
2. Test unitarios: Como en los test unitarios debemos tener certeza del resultado, no podiamos usar estos test para probar metodos que dependieran del resultado de partidas ya que es un evento probabilistico. Por ende mucho debimos probarlo en un main. Además se añadió un main estatico de ejemplos para poder ilustrar el funcionamiento desde allí.
3. Mostrar cada partida en la GUI: Por la razón explicada en la autocrítica, no se logró realizar una interfaz que mostrara el realismo de la lógica, es decir, que cada panel de enfrentamiento fuera en el fondo una partida, en la cual se apreciara el cambio de lado. Entonces se optó por una simplificación de que el panel enfrentamiento simbolizara el enfrentamiento de participantes(que conlleva varias partidas) y para ver el detalle se tiene ver historial (panel creado con ayuda de IA) el cual arroja lo que se imprime en consola (muestra los resultados de forma inmediata).

Autocritica y propuesta de mejoras a futuro  

Nuestro principal problema como equipo fue la división de tareas: Uno hace la lógica y otro la visual, pero no a la par. La dificultad en la lógica no fue el gestor, sino la simulación del torneo con todos los posibles casos, lo que fue mucho trabajo solo para una persona y demoró bastante en terminarse. Y como la visual esperaba una lógica terminada, no se avanzó más allá de un par de generalidades durante el tiempo que se desarrolló la lógica. Esto provocó un trabajo contra el reloj para realizar la visual, donde finalmente tuvimos que trabajar ambos, rompiendo así la metodología inicial. Creemos que una forma más óptima del trabajo en parejas es no esperar a que la otra parte esté lista completamente, sino un desarrollo en paralelo por fragmentos en el proyecto. Por ejemplo, para las inscripciones: la logica de añadir participantes estaba lista tempranamente, la visual podía ya haber creado el panel de aceptar los participantes. 

   









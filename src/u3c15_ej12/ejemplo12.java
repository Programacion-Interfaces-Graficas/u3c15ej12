package u3c15_ej12;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
class tareas implements Runnable { // La clase 'tareas' implementa la interfaz Runnable, necesaria para definir lo que hará el hilo.
    private Thread hilo;
    private String nameHilo;

    public tareas(String nameHilo_) { // Constructor que inicializa el nombre del hilo y crea una instancia de Thread asociada a esta tarea.
        this.nameHilo = nameHilo_;
        this.hilo = new Thread(this); // Se crea un nuevo hilo asociado a la tarea actual (aunque no se usa explícitamente en ExecutorService).
    }

    public void run() { // Método 'run' donde se define la lógica que ejecutará cada hilo.
        System.out.println("Ejecutado: " + nameHilo + " en el hilo: " + hilo.currentThread()); // Imprime el nombre del hilo y su estado.
        try {
            Thread.sleep(2000); // Simula un retardo de 2 segundos para cada tarea.
        } catch (InterruptedException e) {
            e.printStackTrace(); // Manejo de la posible excepción si el hilo es interrumpido durante el sueño.
        }
        System.out.println(nameHilo + " finaliza en el hilo " + hilo.currentThread().getName()); // Imprime cuando el hilo finaliza su ejecución.
    }    
}

public class ejemplo12 {
    public static void main(String [] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3); // Crea un grupo de 3 hilos.
        
        for(int i = 1; i <= 5; i++) { // Ciclo que crea 5 tareas con nombres 'Hilo1', 'Hilo2', etc.
            tareas task = new tareas("Hilo" + i); // Crea una nueva instancia de 'tareas' para cada iteración del ciclo.
            executorService.execute(task); // Envía la tarea al ExecutorService para que se ejecute en uno de los hilos disponibles.
        }
        
        executorService.shutdown(); // Finaliza el ExecutorService después de que todas las tareas hayan sido ejecutadas.
    }
}

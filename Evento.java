import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
/**
 * Representa a un evento del calendario
 * 
 */
public class Evento {
    private String nombre;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private static DateTimeFormatter formateadorFecha = DateTimeFormatter
        .ofPattern("dd/MM/yyyy");
    private static DateTimeFormatter formateadorHora = DateTimeFormatter
        .ofPattern("HH:mm");
    /**
     * A partir de los argumentos recibidos
     * inicializa los atributos de forma adecuada
     * Todos los argumento recibidos son correctos (no hay incoherencias)
     */                 
    public Evento(String nombre, String fecha, String horaInicio,
    String horaFin) {
        this.nombre = nombre;
        this.fecha = LocalDate.parse(fecha, formateadorFecha);
        this.horaInicio = LocalTime.parse(horaInicio, formateadorHora);
        this.horaFin = LocalTime.parse(horaFin, formateadorHora);
    }

    /**
     * accesor para el nombre del evento
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * mutador para el nombre del evento
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * accesor para la fecha del evento
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * mutador para la fecha del evento
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    /**
     * accesor para la hora de inicio del evento
     */
    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    /**
     * mutador para la hora de inicio del evento
     */
    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    /**
     * accesor para la hora de fin del evento
     */
    public LocalTime getHoraFin() {
        return horaFin;
    }

    /**
     * mutador para la hora de fin del evento
     */
    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    /**
     * devuelve nº de día de la semana (1 lunes, 2 martes .... 7 domingo)
     * que se obtendrá a partir de la fecha del evento
     */
    public int getDia() {
        return fecha.getDayOfWeek().getValue();
    }

    /**
     * devuelve el mes (como valor enumerado)
     * que se obtendrá a partir de la fecha del evento
     */
    public Mes getMes() {
        Mes[] listaMeses = Mes.values();
        return listaMeses[fecha.getMonthValue() - 1];
    }

    /**
     * calcula y devuelve la duración del evento en minutos
     */
    public int getDuracion() {
        // Conversor de horas
        int horasInicioAMinutos = (horaInicio.getHour() * 60) + 
            horaInicio.getMinute();
        int horasFinalAMinutos = horaFin.getHour() * 60 + 
            horaFin.getMinute();
        int duracion = horasFinalAMinutos - horasInicioAMinutos;
        return duracion;
    }

    /**
     * Determina si el evento actual va antes (se produce) que
     * el pasado como parámetro
     * 
     * Se tiene en cuenta la fecha y hora de inicio de cada evento
     * Pista! usa un objeto LocalDateTime
     */
    public boolean antesDe(Evento otro) {
        LocalDateTime uno = LocalDateTime.of(fecha, horaInicio);
        LocalDateTime dos = LocalDateTime.of(otro.getFecha(), otro.getHoraInicio());
        if(uno.isBefore(dos)) {
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * representación textual del evento  
     */
    public String toString() {
        return String.format("%8s: %s (Día semana %d)\n", "Nombre", this.nombre, this.getDia()) +
        String.format("%8s: %s\t", "Fecha",
            this.fecha.format(formateadorFecha))   +
        String.format("%s: %s", "Hora inicio",
            this.horaInicio.format(formateadorHora))   +
        String.format("%12s: %s (%d')", "Hora fin",
            this.horaFin.format(formateadorHora),
            this.getDuracion())
        +  "\n------------------------------------------------------";
    }

    /**
     * Código para testea la clase Evento
     */
    public static void main(String[] args) {
        Evento ev1 = new Evento("Examen de programación", "03/02/2021",
                "11:45",
                "13:20");
        System.out.println(ev1.toString());      

        System.out.println();
        Evento ev2 = new Evento("Recoger abrigo en tintorería", "13/03/2021",
                "09:30",
                "10:00");
        System.out.println(ev2.toString());     

        System.out.println();
        Evento ev3 = new Evento("   baluarte Pamplona    negra   ", "29/05/2021",
                "17:00",
                "21:00");
        System.out.println(ev3.toString());

        System.out.println();
        Evento ev4 = new Evento("Comida restaurante europa", "22/05/2021",
                "12:00",
                "17:00");
        System.out.println(ev4.toString());

        System.out.println();
        Evento ev5 = new Evento(" peluquería   ", "29/05/2021",
                "10:20",
                "12:00");
        System.out.println(ev5.toString());

        System.out.println();

        System.out.println("El evento de nombre " + ev3.getNombre() + 
            "\nse produce antes del evento de nombre " + ev5.getNombre() + "? " +
            ev3.antesDe(ev5));
        System.out.println("\nEl evento de nombre " + ev3.getNombre() + 
            "\nse produce después del evento de nombre " + ev5.getNombre() + "? " +
            !(ev3.antesDe(ev5)));
        System.out.println("\nEl evento de nombre " + ev1.getNombre() + 
            "\nse produce antes del evento de nombre " + ev2.getNombre() + "? " +
            ev1.antesDe(ev2));
    }
}
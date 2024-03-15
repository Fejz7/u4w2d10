package progetto;

import progetto.entities.ArchivioBibliotecario;
import progetto.entities.ElementoCatalogo;
import progetto.entities.Libro;
import progetto.entities.Rivista;

import java.io.IOException;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        ArchivioBibliotecario archivio = new ArchivioBibliotecario();

        try {
            archivio.aggiungiElemento(new Libro("1234567890", "Il Signore degli Anelli", 1954, 1170, "J.R.R. Tolkien", "Fantasy"));
            archivio.aggiungiElemento(new Libro("0987654321", "Guerra e Pace", 1869, 1225, "Lev Tolstoj", "Romanzo"));
            archivio.aggiungiElemento(new Libro("5678901234", "Il processo", 1925, 255, "Franz Kafka", "Romanzo"));
            archivio.aggiungiElemento(new Rivista("9876543210", "National Geographic", 2023, 100, Rivista.Periodicita.MENSILE));

            System.out.println("Ricerca per ISBN:");
            ElementoCatalogo elementoPerISBN = archivio.cercaPerISBN("1234567890");
            elementoPerISBN.visualizzaDettagli();

            System.out.println("\nRicerca per anno di pubblicazione:");
            List<ElementoCatalogo> libriAnno = archivio.cercaPerAnno(1954);
            for (ElementoCatalogo libro : libriAnno) {
                libro.visualizzaDettagli();
            }

            System.out.println("\nRicerca per autore (libri):");
            List<ElementoCatalogo> libriAutore = archivio.cercaPerAutore("J.R.R. Tolkien");
            for (ElementoCatalogo libro : libriAutore) {
                libro.visualizzaDettagli();
            }


            System.out.println("\nAltri libri presenti nell'archivio:");
            for (ElementoCatalogo elemento : archivio.getCatalogo()) {
                if (elemento instanceof Libro) {
                    elemento.visualizzaDettagli();
                }
            }


            System.out.println("\nRicerca per ISBN (Rivista):");
            ElementoCatalogo rivista = archivio.cercaPerISBN("9876543210");
            rivista.visualizzaDettagli();

            archivio.salvaSuDisco("archivio.dat");
            System.out.println("\nArchivio salvato su disco.");

            ArchivioBibliotecario archivioCaricato = new ArchivioBibliotecario();
            archivioCaricato.caricaDaDisco("archivio.dat");
            System.out.println("Archivio caricato da disco.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

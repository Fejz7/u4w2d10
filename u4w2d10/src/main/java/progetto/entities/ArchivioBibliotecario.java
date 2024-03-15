package progetto.entities;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ArchivioBibliotecario implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<ElementoCatalogo> catalogo;

    public ArchivioBibliotecario() {
        this.catalogo = new ArrayList<>();
    }

    public void aggiungiElemento(ElementoCatalogo elemento) {
        catalogo.add(elemento);
    }

    public void rimuoviElemento(String codiceISBN) throws NoSuchElementException {
        catalogo.removeIf(elemento -> elemento.getCodiceISBN().equals(codiceISBN));
    }

    public ElementoCatalogo cercaPerISBN(String codiceISBN) throws NoSuchElementException {
        return catalogo.stream()
                .filter(elemento -> elemento.getCodiceISBN().equals(codiceISBN))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    public List<ElementoCatalogo> cercaPerAnno(int anno) {
        return catalogo.stream()
                .filter(elemento -> elemento.getAnnoPubblicazione() == anno)
                .toList();
    }

    public List<ElementoCatalogo> cercaPerAutore(String autore) {
        return catalogo.stream()
                .filter(elemento -> elemento instanceof Libro)
                .filter(elemento -> ((Libro) elemento).getAutore().equals(autore))
                .toList();
    }

    public void salvaSuDisco(String filePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(catalogo);
        }
    }

    @SuppressWarnings("unchecked")
    public void caricaDaDisco(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            catalogo = (List<ElementoCatalogo>) ois.readObject();
        }
    }

    public List<ElementoCatalogo> getCatalogo() {
        return catalogo;
    }
}

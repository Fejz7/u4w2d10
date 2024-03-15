package progetto.entities;

public class Libro extends ElementoCatalogo {
    private String autore;
    private String genere;

    public Libro(String codiceISBN, String titolo, int annoPubblicazione, int numeroPagine, String autore, String genere) {
        super(codiceISBN, titolo, annoPubblicazione, numeroPagine);
        this.autore = autore;
        this.genere = genere;
    }

    public String getAutore() {
        return autore;
    }

    public String getGenere() {
        return genere;
    }

    @Override
    public void visualizzaDettagli() {
        System.out.println("Libro: " + getTitolo() + " - Autore: " + autore + " - Genere: " + genere);
    }
}

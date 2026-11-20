package biblioteca.com.biblioteca.model;

public class Autor {
    private Long id;
    private String nome;
    private String nacionalidade;
    private String obrasPublicadas;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public String getNacionalidade() { return nacionalidade; }

    public void setNacionalidade(String nacionalidade) { this.nacionalidade = nacionalidade; }

    public String getObrasPublicadas() { return obrasPublicadas; }

    public void setObrasPublicadas(String obrasPublicadas) { this.obrasPublicadas = obrasPublicadas; }
}

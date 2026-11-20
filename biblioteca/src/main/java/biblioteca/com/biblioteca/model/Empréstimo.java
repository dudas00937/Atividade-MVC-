package biblioteca.com.biblioteca.model;

import java.time.LocalDate;

public class Emprestimo {
    private Long id;
    private String livro;
    private String usuario;
    private LocalDate dataRetirada;
    private LocalDate dataDevolucao;

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getLivro() { return livro; }

    public void setLivro(String livro) { this.livro = livro; }

    public String getUsuario() { return usuario; }

    public void setUsuario(String usuario) { this.usuario = usuario; }

    public LocalDate getDataRetirada() { return dataRetirada; }

    public void setDataRetirada(LocalDate dataRetirada) { this.dataRetirada = dataRetirada; }

    public LocalDate getDataDevolucao() { return dataDevolucao; }

    public void setDataDevolucao(LocalDate dataDevolucao) { this.dataDevolucao = dataDevolucao; }
}

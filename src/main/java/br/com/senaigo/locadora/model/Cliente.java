package br.com.senaigo.locadora.model;

import br.com.senaigo.locadora.interfaces.PersisteDados;
import br.com.senaigo.locadora.utils.Utils;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Cliente extends PersisteDados {
    
    //Atributos
    private String nome;
    private String razaoSocial;
    private String nomeFantasia;
    private LocalDate dataNascimento;
    private String cpf;
    private String cnpj;
    private Endereco endereco;
    private Telefone telefonePrincipal;
    private Telefone telefoneAlternativo;
    private String email;
    
    //Construtores
    public Cliente() {
        super();
        this.nome = "";
        this.razaoSocial = "";
        this.nomeFantasia = "";
        this.dataNascimento = null;
        this.cpf = "";
        this.cnpj = "";
        this.endereco = null;
        this.telefonePrincipal = null;
        this.telefoneAlternativo = null;
        this.email = "";
    }
    
     //Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Telefone getTelefonePrincipal() {
        return telefonePrincipal;
    }

    public void setTelefonePrincipal(Telefone telefonePrincipal) {
        this.telefonePrincipal = telefonePrincipal;
    }

    public Telefone getTelefoneAlternativo() {
        return telefoneAlternativo;
    }

    public void setTelefoneAlternativo(Telefone telefoneAlternativo) {
        this.telefoneAlternativo = telefoneAlternativo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    //Métodos Próprios
    
    //Métodos herdados
    @Override
    public void monteObjeto(String dadosDoObjeto) {
        List<String> campos = Utils.obtenhaCampos(dadosDoObjeto);

        this.id = Utils.convertaParaInt(campos.get(0));
        this.nome = campos.get(1);
        this.razaoSocial = campos.get(2);
        this.dataNascimento = LocalDate.parse(campos.get(3));
        this.cpf = campos.get(4);
        this.cnpj = campos.get(5);
        Endereco endereco = (Endereco) PersisteDadosFactory.obtenhaInstancia("Endereco");
        String dadosEndereco = campos.get(6) + ";" + campos.get(7) + ";" +campos.get(8) + ";" +campos.get(9) + ";" + campos.get(10);
        endereco.monteObjeto(dadosEndereco);
        this.endereco = endereco;
        Telefone telefonePrincipal = (Telefone) PersisteDadosFactory.obtenhaInstancia("Telefone");
        String dadosTelefonePrincipal = campos.get(11) + ";" + campos.get(12);
        telefonePrincipal.monteObjeto(dadosTelefonePrincipal);
        this.telefonePrincipal = telefonePrincipal;
        Telefone telefoneAlternativo = (Telefone) PersisteDadosFactory.obtenhaInstancia("Telefone");
        String dadosTelefoneAlternativo = campos.get(13) + ";" + campos.get(14);
        telefoneAlternativo.monteObjeto(dadosTelefoneAlternativo);
        this.telefoneAlternativo = telefoneAlternativo;
        this.email = campos.get(15);
    }

    @Override
    public String desmonteObjeto(boolean comParametro) {
        StringBuilder dadosSeparadosPorPontoVirgula = new StringBuilder();

        if(comParametro) {
            dadosSeparadosPorPontoVirgula.append(obtenhaParametros());
        }

        dadosSeparadosPorPontoVirgula.append(this.id).append(";");
        dadosSeparadosPorPontoVirgula.append(this.nome).append(";");
        dadosSeparadosPorPontoVirgula.append(this.razaoSocial).append(";");
        dadosSeparadosPorPontoVirgula.append(this.dataNascimento.toString()).append(";");
        dadosSeparadosPorPontoVirgula.append(this.cpf).append(";");
        dadosSeparadosPorPontoVirgula.append(this.cnpj).append(";");
        dadosSeparadosPorPontoVirgula.append(this.endereco.desmonteObjeto(false)).append(";");
        dadosSeparadosPorPontoVirgula.append(this.telefonePrincipal.desmonteObjeto(false)).append(";");
        dadosSeparadosPorPontoVirgula.append(this.telefoneAlternativo.desmonteObjeto(false)).append(";");
        dadosSeparadosPorPontoVirgula.append(this.email);


        return dadosSeparadosPorPontoVirgula.toString();
    }
    
}

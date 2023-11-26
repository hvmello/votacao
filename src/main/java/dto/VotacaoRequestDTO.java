package dto;

public class VotacaoRequestDTO {

    private String name;

    public VotacaoRequestDTO(){

    }

    public VotacaoRequestDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}

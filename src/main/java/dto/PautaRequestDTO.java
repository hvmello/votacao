package dto;

public class PautaRequestDTO {

    private String name;

    public PautaRequestDTO(){

    }

    public PautaRequestDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}

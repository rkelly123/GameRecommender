package model;

public class Video_Game_Studio {
    protected Integer company_id;
    protected String company_name;

    public Video_Game_Studio(Integer company_id, String company_name) {
        this.company_id = company_id;
        this.company_name = company_name;
    }

    public Integer getCompanyId() { return company_id; }

    public String getCompanyName() {
        return company_name;
    }
}

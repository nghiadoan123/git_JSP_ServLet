package bean.contract;

public class ContractDetail {
    private int id;
    private int quantity;
    private Contract contractId;
    private AttachService attachServiceId;

    public ContractDetail() {
    }

    public ContractDetail(int id, int quantity, Contract contractId, AttachService attachServiceId) {
        this.id = id;
        this.quantity = quantity;
        this.contractId = contractId;
        this.attachServiceId = attachServiceId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Contract getContractId() {
        return contractId;
    }

    public void setContractId(Contract contractId) {
        this.contractId = contractId;
    }

    public AttachService getAttachServiceId() {
        return attachServiceId;
    }

    public void setAttachServiceId(AttachService attachServiceId) {
        this.attachServiceId = attachServiceId;
    }
}

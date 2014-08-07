package com.schmapty.guvnah.model.result;


public interface SortedResultDTO extends ResultDTO {
/*
	private static final long serialVersionUID = 1256433978545566160L;

	private ResultDTO key;
	private List<ResultDTO> value;
	
	public SortedResultDTO() {
	}
	
	public SortedResultDTO(ResultDTO key) {
		this.key=key;
	}
	
	public ResultDTO getKey() {
		return key;
	}
	public void setKey(ResultDTO key) {
		this.key = key;
	}
	public List<ResultDTO> getValue() {
		return value;
	}
	public void setValue(List<ResultDTO> value) {
		this.value = value;
	}
	public SortedResultDTO addValue(ResultDTO dto) {
		if(this.value==null) {
			this.value = new ArrayList<ResultDTO>();
		}
		this.value.add(dto); 
		return this;
	}
	
	@Override
	public int compareTo(ResultDTO o) {
		if(!(o instanceof SortedResultDTO)) return 0;
		SortedResultDTO r = (SortedResultDTO)o;
		if(this.key==null && r.getKey() != null) {
			return -1;
		} else {
			return this.key.compareTo(r.getKey());
		}
	}
	*/
}

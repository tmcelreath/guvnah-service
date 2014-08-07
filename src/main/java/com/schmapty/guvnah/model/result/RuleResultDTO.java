package com.schmapty.guvnah.model.result;

import java.util.ArrayList;
import java.util.List;

public class RuleResultDTO implements SortedResultDTO {

	private static final long serialVersionUID = 1275738986312059365L;

	private RuleDTO key;
	private List<ElementDTO> value;
	
	public RuleResultDTO() {
		super();
	}
	
	public RuleResultDTO(RuleDTO rule) {
		this.key = rule;
	}

	public RuleDTO getKey() {
		return key;
	}

	public void setKey(RuleDTO key) {
		this.key = key;
	}

	public List<ElementDTO> getValue() {
		return value;
	}

	public void setValue(List<ElementDTO> value) {
		this.value = value;
	}

	
	public RuleResultDTO addValue(ElementDTO dto) {
		if(this.value==null) {
			this.value = new ArrayList<ElementDTO>();
		}
		this.value.add(dto); 
		return this;
	}

	public int compareTo(ResultDTO o) {
		if(!(o instanceof ElementResultDTO)) return 0;
		RuleResultDTO r = (RuleResultDTO)o;
		if(this.key==null && r.getKey() != null) {
			return -1;
		} else {
			return this.key.compareTo(r.getKey());
		}
	}
	

}

package nj.zj.study;

public class User {
	private String name;
	private String phone;
	private String idcard;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	
	
	 @Override  
	    public int hashCode() {  
	        final int prime = 31;  
	        int result = 1;  
	       // result = prime * result + ((id == null) ? 0 : id.hashCode());  
	        result = prime * result + ((name == null) ? 0 : name.hashCode());  
	        result = prime * result + ((phone == null) ? 0 : phone.hashCode());  
	        result = prime * result + ((idcard == null) ? 0 : idcard.hashCode());  
	        return result;  
	    }  
	  
	    @Override  
	    public boolean equals(Object obj) {  
	        if (this == obj) {  
	            return true;  
	        }  
	        if (obj == null) {  
	            return false;  
	        }  
	        if (getClass() != obj.getClass()) {  
	            return false;  
	        }  
	        User other = (User) obj;  
	        if (idcard == null) {  
	            if (other.idcard != null) {  
	                return false;  
	            }  
	        } else if (!idcard.equals(other.idcard)) {  
	            return false;  
	        }  
	        if (name == null) {  
	            if (other.name != null) {  
	                return false;  
	            }  
	        } else if (!name.equals(other.name)) {  
	            return false;  
	        }  
	        if (phone == null) {  
	            if (other.phone != null) {  
	                return false;  
	            }  
	        } else if (!phone.equals(other.phone)) {  
	            return false;  
	        }  
	        
	        return true;  
	    }  
	
	

	
}

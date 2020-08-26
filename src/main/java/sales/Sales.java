package sales;

import com.sun.org.apache.xpath.internal.operations.Bool;
import java.util.Date;

public class Sales {
	
	private boolean isSupervisor;

	private boolean isActive;

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean active) {
		isActive = active;
	}

	public boolean isSupervisor() {
		return isSupervisor;
	}

	public void setSupervisor(boolean isSupervisor) {
		this.isSupervisor = isSupervisor;
	}
}

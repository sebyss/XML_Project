package XML_Project_MVN.projectArtifact;

import org.apache.camel.main.*;

public final class UserMain {

	private UserMain() {
		// to comply with checkstyle rule
	}

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {
		Main main = new Main();
		main.bind("userService", new UserService());
		main.addRouteBuilder(new UserRouteBuilder());
		main.run();
	}
}

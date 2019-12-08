package christian;

/**
 * @author Christian McCann 12/7/2019 This class is used to generate usernames and emails as well as
 * check if a password has the valid requirements.
 */
public class Employee {

  private String name;
  private String userName;
  private String email;
  private String password;

  /**
   * This constructor takes the fullname and password and checks to see if they are both valid by
   * implementing the following methods. This Constructor also sets the username and email to
   * defaults if they are not correct.
   *
   * @param name     persons ful name
   * @param password proposed password
   */
  public Employee(String name, String password) {
    this.name = name;
    if (checkName(name)) {
      setUsername(this.name);
      setEmail(name);
    } else {
      userName = "default";
      email = "user@oracleacademy.Test";
    }
    isValidPassword(password);
  }

  /**
   * This method checks to see if the user entered both a first and lask name. The method first
   * checks to see if there is a space in the name string. if there is, then it checks to see if
   * that space is at the end of the entered name. That way it can make sure there is a first and
   * last name.
   *
   * @param name persons full name
   * @return
   */
  public boolean checkName(String name) {
    if (name.contains(" ") && name.length() - 1 != name.indexOf(" ")) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * This setUsername method takes the name from the constructor if it has the correct format and
   * splits the name into a first and last name. Those are then sent to an array where the separate
   * elements are appended together in a username format.
   *
   * @param name persons full name
   */
  public void setUsername(String name) {
    String[] nameArray = name.split(" ");
    userName = nameArray[0].substring(0, 1) + nameArray[1];
    userName = userName.toLowerCase();
  }

  /**
   * This setUsername method takes the name from the constructor if it has the correct format and
   * splits the name into a first and last name. Those are then sent to an array where the separate
   * elements are appended together in an email format.
   *
   * @param name persons full name
   */
  public void setEmail(String name) {
    String[] nameArray = name.split(" ");
    email = nameArray[0] + "." + nameArray[1];
    email = email.toLowerCase() + "@oracleacademy.Test";
  }

  /**
   * This method checks to see if the password entered by the user passes three different criteria.
   * If the password is valid, then the password does not change, but if the password does not meet
   * the criteria, then a default password is used.
   *
   * @param password proposed password
   */
  public void isValidPassword(String password) {
    char c;
    boolean capitalFlag = false;
    boolean lowerCaseFlag = false;
    boolean specialCharacterFlag = false;
    for (int i = 0; i < password.length(); i++) {
      c = password.charAt(i);
      if (!Character.isDigit(c) && !Character.isLetter(c)) {
        specialCharacterFlag = true;
      } else if (Character.isUpperCase(c)) {
        capitalFlag = true;
      } else if (Character.isLowerCase(c)) {
        lowerCaseFlag = true;
      }
    }
    if (specialCharacterFlag && capitalFlag && lowerCaseFlag) {
      this.password = password;
    } else {
      this.password = "pw";
    }

  }

  public String getUserName() {
    return userName;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  /**
   * this toString method overrides the default toString giving each of the fields a nice format.
   *
   * @return
   */
  @Override
  public String toString() {
    return "Employee Details\nName : " + name + "\nUsername : " + userName + "\nEmail :"
        + " " + email + "\nInitial Password : " + password;
  }
}

  package kg.adam.faculty_satisfaction_survey.course.domain.model;

  /**
   * Enum representing gender options for a student.
   */
  public enum Gender {
      MALE("Мужской"),
      FEMALE("женский");

      private final String name;

      Gender(String name) {
          this.name = name;
      }

      public String getName() {
          return name;
      }
  }
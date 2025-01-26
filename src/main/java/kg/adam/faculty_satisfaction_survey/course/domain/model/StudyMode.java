  package kg.adam.faculty_satisfaction_survey.course.domain.model;

  /**
   * Enum representing modes of study for a student.
   */
  public enum StudyMode {
      FULL_TIME("Очное"),
      PART_TIME("Заочное"),
      MASTERS("Магистратура");

      private final String name;

      StudyMode(String name) {
          this.name = name;
      }

      public String getName() {
          return name;
      }
  }
public class AgeGroup {
    public String getAgeGroup(int age) {
        if (age < 18) return "Child";
        else if (age < 60) return "Adult";
        else return "Senior";
    }
}
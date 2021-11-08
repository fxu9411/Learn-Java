
public class SalaryIncome extends Income{

	public SalaryIncome(double income) {
		super(income);
	}
	// TODO

	@Override
	public double getTax() {
		if (this.income <= 5000) {
            return 0;
        }
        return (this.income - 5000) * 0.2;
	}
}

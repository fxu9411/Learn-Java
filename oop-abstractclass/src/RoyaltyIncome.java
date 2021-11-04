
/**
 * 稿费收入税率是20%
 */
public class RoyaltyIncome extends Income{
	
	public RoyaltyIncome(double income) {
		super(income);
	}

	// TODO
	@Override
	public double getTax() {
		return this.income * 0.2;
	}
}

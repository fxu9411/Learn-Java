
/**
 * 稿费收入税率是20%
 */
public class RoyaltyIncome extends Income {
	
	public RoyaltyIncome(int income) {
		super(income);
	}
	// TODO

	@Override
	public double getTax() {
		return this.income * 0.2;
	}
}

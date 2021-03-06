import java.util.*;

public class EmployeeWage implements IComputeEmpWage{
	//constants
	public static final int IS_PART_TIME=1; 
	public static final int IS_FULL_TIME=2;  

	//variables
	public List<CompanyEmpWage> companyEmpWageList=new ArrayList<CompanyEmpWage>();
	public Map<String,CompanyEmpWage> companyToEmpWageMap=new HashMap<>();

	public void addCompanyEmpWage(String company, int empRatePerHour, int numOfWorkingDays, int maxHrsPerMonth){
		CompanyEmpWage employee=new CompanyEmpWage(company,empRatePerHour,numOfWorkingDays,maxHrsPerMonth);
		companyEmpWageList.add(employee);
		companyToEmpWageMap.put(company,employee);
	}
		
	public void computeEmpWage(){
		for(CompanyEmpWage employee:companyEmpWageList){
			this.computeEmpWage(employee);
			System.out.println(employee);
		}
	}

	public int getTotalWage(String company){
		for(String compName:companyToEmpWageMap.keySet()){
			if(company.equals(compName)){
				CompanyEmpWage employee=companyToEmpWageMap.get(compName);
				return employee.totalEmpWage;
			}
		}
		return 0;
	}

	public void computeEmpWage(CompanyEmpWage employee){
		//variables
		int empHrs=0;
		int totalEmpHrs=0;
		int totalWorkingDays=0;
		int dailyWage=0;

		//computation
		while(totalEmpHrs<=employee.maxHrsPerMonth && totalWorkingDays<employee.numOfWorkingDays)
		{
			totalWorkingDays++;
			double empCheck=Math.floor(Math.random()*10)%3;
			switch((int)empCheck)
			{
				case IS_FULL_TIME:
					empHrs=8;
					break;
				case IS_PART_TIME:
					empHrs=4;
					break;
				default:
					empHrs=0;
			}
			dailyWage=empHrs*employee.empRatePerHour;
			totalEmpHrs+=empHrs;
			System.out.println("Day#: "+totalWorkingDays+" Emp Hr: "+empHrs+" Daily Wage: "+dailyWage);
		}
		employee.totalEmpWage=totalEmpHrs*employee.empRatePerHour;
	}
	
	

	public static void main(String[] args){
		System.out.println("Welcome to Employee Wage Computation Program");
		EmployeeWage empWage=new EmployeeWage();
		empWage.addCompanyEmpWage("DMart",20,2,10);	
		empWage.addCompanyEmpWage("Reliance",10,4,20);
		empWage.computeEmpWage();
		System.out.println("Total Wage for Reliance Company: "+empWage.getTotalWage("Reliance"));
	}
}
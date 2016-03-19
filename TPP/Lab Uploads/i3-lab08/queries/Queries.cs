using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace TPP.Laboratory.Functional.Lab08 {

    class Query {

        private Model model = new Model();

        private static void Show<T>(IEnumerable<T> collection) {
            foreach (var item in collection) {
                Console.WriteLine(item);
            }
            Console.WriteLine("Number of items in the collection: {0}.", collection.Count());
        }

        static void Main(string[] args) {
            Query query = new Query();
            query.Query1();
            Console.WriteLine("\n");
            query.Query2();
            Console.WriteLine("\n");
            query.Query3();
            Console.WriteLine("\n");
            query.Query4();
            Console.WriteLine("\n");
            query.Query5();

            Console.WriteLine("\n");
            query.Homework1();

            Console.WriteLine("\n");
            query.Homework2();

            Console.WriteLine("\n");
            query.Homework3();

            Console.WriteLine("\n");
            query.Homework4();

            Console.WriteLine("\n");
            query.Homework5();
        }

        private void Query1() {
            // Modify this query to show the names of the employees older than 50 years

            var employees = model.Employees.Where(emp => emp.Age > 50).Select(emp => emp.Name);
            //var employees = from employee in model.Employees
            //                where employee.Age > 50
            //                select employee.Name;

            Console.WriteLine("Query1: Employees:");
            Show(employees);
        }

        private void Query2() {
            // Show the name and email of the employees who work in Asturias 

            //var info = model.Employees.Where(emp => emp.Province.Equals("Asturias")).Select(emp => new Tuple<int, string>(emp.Age, emp.Email ));   
            var info = model.Employees.Where(emp => emp.Province.Equals("Asturias")).Select(emp => new { emp.Age, emp.Email });

            Console.WriteLine("Query2: Employees who work in Asturias info:");
            Show(info);
        }

        // Notice: from now on, check out http://msdn.microsoft.com/en-us/library/9eekhta0.aspx

        private void Query3() {
            // Show the names of the departments with more than one employee 18 years old and beyond; 
            // the department should also have any office number starting with "2.1"

            var departments = model.Departments.Where(dep => dep.Employees.Select(emp => emp.Age >= 18).Count() > 1)
                                                .Where(dep => dep.Employees.Any(emp => emp.Office.Number.StartsWith("2.1")));

            //var depart = from department in model.Departments
            //             where department.Employees.Where(x => x.Age > 18).Count() > 1
            //             where department.Employees.Any(emp => emp.Office.Number.StartsWith("2.1")
            //             select department.Name;

            Console.WriteLine("Query3: Departments with employees 18 or beyond which office number starts with 2.1:");
            Show(departments);

        }

        private void Query4() {
            // Show the phone calls of each employee. 
            // Each line should show the name of the employee and the phone call duration in seconds.

            // calculates calls FOR EVERY employee, quadratic performance, worse
            var results = from employee in model.Employees
                          from call in model.PhoneCalls
                          where employee.TelephoneNumber.Equals(call.SourceNumber)
                          select new { employee.Name, call.Seconds };

            Console.WriteLine("Query4: Name of the employee and phone call duration");
            Show(results);

            results = from employee in model.Employees
                      join call in model.PhoneCalls
                      on employee.TelephoneNumber equals call.SourceNumber
                      select new { employee.Name, call.Seconds };

            Console.WriteLine("Query4: Name of the employee and phone call duration");
            Show(results);


        }

        private void Query5() {
            // Show, grouped by each province, the name of the employees 
            // (both province and employees must be lexicographically ordered)          

            var result = from employee in model.Employees
                         orderby employee.Name, employee.Province
                         group employee by employee.Province into g
                         select g.Select(emp => emp.Name + " " + emp.Surname);                         

            Console.WriteLine("Query5:");
            Show(result.SelectMany(x => x));

        }

        /************ Homework **********************************/

        private void Homework1() {
            // Show, ordered by age, the names of the employees in the Computer Science department, 
            // who have an office in the Faculty of Science, 
            // and who have done phone calls longer than one minute


            var results = model.Employees
                .Join(model.PhoneCalls, emp => emp.TelephoneNumber, call => call.SourceNumber,
                    (emp, call) => new { emp.Name, emp.Age, emp.Department, emp.Office, emp.TelephoneNumber, call.Seconds })
                .Where(emp => emp.Office.Building.Equals("Faculty of Science"))
                .Where(emp => emp.Department.Name.Equals("Computer Science"))
                .Where(call => call.Seconds > 60)
                .OrderBy(emp => emp.Age);

            Console.WriteLine("Homework1: ");
            Show(results);
        }

        private void Homework2() {
            // Show the summation, in seconds, of the phone calls done by the employees of the Computer Science department

            var result = model.Employees.Where(emp => emp.Department.Name.Equals("Computer Science"))
                .Join(model.PhoneCalls, emp => emp.TelephoneNumber, call => call.SourceNumber, (emp, call) => call.Seconds)
                .Aggregate((res, secs) => res += secs);

            Console.WriteLine("Homework2: ");
            Console.WriteLine(result);

        }

        private void Homework3() {
            // Show the phone calls done by each department, ordered by department names. 
            // Each line must show “Department = <Name>, Duration = <Seconds>”

            var results = model.Employees.Join(model.PhoneCalls, emp => emp.TelephoneNumber, call => call.SourceNumber, 
                (emp, call) => new { Department = emp.Department.Name, Duration = call.Seconds })
                .OrderBy(join => join.Department);

            Console.WriteLine("Homework3: ");
            Show(results);
        }

        private void Homework4() {
            // Show the departments with the youngest employee, 
            // together with the name of the youngest employee and his/her age 
            // (more than one youngest employee may exist)

            var results = model.Employees.Where(emp => emp.Age == model.Employees.Min(minEmp => minEmp.Age))
                .Select(emp => new { Department = emp.Department, Name = emp.Name + " " + emp.Surname, Age = emp.Age});

            Console.WriteLine("Homework4: ");
            Show(results);
        }

        private void Homework5() {
            // Show the greatest summation of phone call durations, in seconds, 
            // of the employees in the same department, together with the name of the department 
            // (it can be assumed that there is only one department fulfilling that condition)

            var results = from emp1 in model.Employees
                          from emp2 in model.Employees
                          where !emp1.Equals(emp2)
                          where emp1.Department.Name.Equals(emp2.Department.Name)                          
                          join call in model.PhoneCalls
                          on new { T1 = emp1.TelephoneNumber, T2 = emp2.TelephoneNumber }
                                equals new { T1 = call.SourceNumber, T2 = call.DestinationNumber }
                          select new { DepName = emp1.Department, Duration = call.Seconds, NameA = emp1.Name, NameB = emp2.Name };

            Console.WriteLine("Homework5: ");
            Show(results);
        }


    }

}

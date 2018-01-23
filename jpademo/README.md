Day 2 	
___________________________________
Topic: Java Persistance API
____________________________________



mvn archetype:create -DarchetypeGroupId=org.apache.maven.archetypes -DgroupId=com.demo -DartifactId=SampleJpa

mvn dependency:copy-dependencies -DoutputDirectory=${project.build.directory}/lib

dependencies:
---------------

	

		<dependency>
			<groupId>org.hibernate</groupId>
			<artifactId>hibernate-core</artifactId>
			<version>4.3.1.Final</version>
		</dependency>

		<dependency>
			<groupId>org.hibernate</groupId>
			<artifactId>hibernate-entitymanager</artifactId>
			<version>4.3.1.Final</version>
		</dependency>
		<dependency>
            		<groupId>mysql</groupId>
            		<artifactId>mysql-connector-java</artifactId>
            		<version>5.1.10</version>
        	</dependency>


	What is JPA?
	____________________
	# JSR 220
	# provide standard way to do ORM
	# Many implementation vendors: hibernate, TopLink, eclipseLink etc



	What are Entities?
	_______________________

	# POJO- used to store data in db

	# Entities-Persisted, transaction enable, hv identity





	Entity Manager
	___________________

	==> API that is used to access entitites in an application persistance context

	==> Manage life cycle of entities

	==>Act as bridge bw oo world and DB world



		save			result
	Object	--------> Entity mgr ---------->  DB
		<-------	    <---------
		 retrive		SQL




	Transaction type
	_____________________

	==>Resource local

	==>JTA






____________________________________
Hands On:
____________________________________

	1. Hello World JPA

	2. CRUD

	3. Primary key generation strategy

	4. More annotations:

	5. Mapping relationships
		One to many
		one to one
		many to many 

	6. Inheritance strategies

	7. JPQL

____________________________________






____________________________________

	1. Hello World JPA
____________________________________


	Steps in JPA
	--------------
	1.Create an JPA project and add jar to classpath

	2.Create POJO annotate with annotations


	class Customer{
		private int customerId;
		private String customerName;
		private String customerAddress;

	}


	@Entity
	@Table(name="customer")
	public class Customer {
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		private int customerId;
		private String customerName;
		private String customerAddress;
	}


	3.Update persistence.xml


	<?xml version="1.0" encoding="UTF-8"?>
	<persistence version="2.0" xmlns="http://java.sun.com/xml/ns/persistence" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 	xsi:schemaLocation="http://java.sun.com/xml/ns/persistence 	http://java.sun.com/xml/ns/persistence/persistence_2_0.xsd">
	<persistence-unit name="demo_jpa" transaction-type="RESOURCE_LOCAL">
		<class>com.model.Customer</class>
		<properties>
			<property name="javax.persistence.jdbc.url" 	value="jdbc:mysql://localhost:3306/foo"/>
			<property name="javax.persistence.jdbc.user" value="root"/>
			<property name="javax.persistence.jdbc.password" value="root"/>
			<property name="javax.persistence.jdbc.driver" value="com.mysql.jdbc.Driver"/>
			<property name="hibernate.hbm2ddl.auto" value="update"/>
		</properties>
	</persistence-unit>
	</persistence>




	
	Write hello wrold to add customer
	-----------------------------------

		EntityManagerFactory fc=Persistence.createEntityManagerFactory("demo_jpa");
		EntityManager em=fc.createEntityManager();
		
		em.getTransaction().begin();
		
		Customer c=new Customer();
		c.setCustomerId(22);
		c.setName("raja");
		c.setEmail("raja.raja.com");
		
		em.persist(c);
		System.out.println("inserted...");
		em.getTransaction().commit();
		em.close();
		fc.close();



	Show All
	--------------------------------

	EntityManagerFactory fc=Persistence.createEntityManagerFactory("customer");
	EntityManager em=fc.createEntityManager();
	List<Customer>list=
		entityManager.createQuery("select c from Customer c",Customer.class).
		getResultList();


		for (Customer customer : list) {
			System.out.println(customer.getName());
		}




	Update
	---------
		Customer c=em.find(Customer.class, 2);
		System.out.println(c.getCustomerAddress());
		
		c.setCustomerAddress("bombay");
		
		em.getTransaction().commit();

	

		Or simply : em.merge(e);


	Delete
	-----------
		Customer c=em.find(Customer.class, 22);
		em.remove(c);






____________________________________

	2. JPA CRUD
____________________________________


Hello World:

	Follow DAO DTO pattern.


	public interface CustomerDao {

	public List<Customer>getAllCustomer();
	public void addCustomer(Customer c);
	public void removeCustomer(Customer c);
	public Customer getCustomerById(int id);
	public void updateCustomer(Customer c);
	}





	public class CustomerDaoImp implements CustomerDao {

	private EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Customer> getAllCustomer() {

		List<Customer> clist = entityManager.createQuery(
				"select c from Customer c", Customer.class).getResultList();

		return clist;
	}

	@Override
	public void addCustomer(Customer c) {
		entityManager.persist(c);
	}

	@Override
	public void removeCustomer(Customer c) {
		entityManager.remove(c);
	}

	@Override
	public Customer getCustomerById(int id) {
		return entityManager.find(Customer.class, id);
		
	}

	@Override
	public void updateCustomer(Customer c) {
		entityManager.merge(c);
	}

	}










_____________________________________

3. Primary key genearation strategy
_______________________________________



	@GeneratedValue(strategy=
				AUTO (By default :let hibernate make decision)

				Identity (Hibernate use identity column)

				Sequence

				Table



	Example table generation strategy
	------------------------------------------


	@Id
	@TableGenerator	(name="empid",table="emppktable",pkColumnName="empkey",
			pkColumnValue="empvalue",allocationSize=1)

	@GeneratedValue(strategy=GenerationType.TABLE,generator="empid")
	@Column(name="emp_ID")










______________________________________________________________

3. More annoations

	@Basic, @Transient @Temporal @Table @Column @Lob etc...

______________________________________________________________



Consider:

	@Entity
	class Customer{
			@Id
			private int customerId;
			private String customerName;
			private String customerAddress;

		}



	What if table name and field name is something else?
	----------------------------------------------------



	@Entity(name="customer-entity")
	@Table(name="customer-table")
	class Customer{
			@Id
			Column(name="customer_id")
			private int customerId;

			@Basic
			private String customerName;
			private String customerAddress;

		}


		Note: annotations can be applied to getters



	Add some more fileds to User bean
	--------------------------------------
	private Date joinedDate;  //java.util
	private String address;
	private String description;


	Check and run !


	@Basic ?
		used to add some properties to add to that field.
		You can work apply without it.

	@Transient?
		You can not store static and transient data.






	What if i want only to store date part not time?
	-----------------------------------------

	use @Tomporal

		@Temporal(TemporalType.DATE)
			private Date joinedDate;  //java.util


	@Lob
		private String description;

		blob/clob









______________________________________________________________

4. Mapping relationships
		many to one
		one to one
		many to many 
______________________________________________________________



	Many-To-One Mapping
	______________________

	Consider:-

	target <------------------- source
	

		1			N
	Department ------<>---------Employee

	  

	What is the ERD rule: put PK from 1 side to N side

	ie Put PK of Department going to put as FK in Employee table


	So that Employee is source entity and Department is target entities.




	@Entity
	public class Employee {

		@Id
		@GeneratedValue
		private int empId;
		private String empName;

		@ManyToOne
		private Department department;



	@Entity
	public class Department {
		@Id
		@GeneratedValue
		private int deptId;
		private String detpName;
	
		@OneToMany(mappedBy="department")
		private Collection<Employee>employees=new ArrayList<Employee>();
	


	Now controlling more to it
	------------------------------------
	The PK of Department is put as FK in employee table with 
	
	strange name of department_deptId

	I want to have some other name
	-----------------------------
		deptId_fk  what to do?




	@Entity
	public class Employee {

		@Id
		@GeneratedValue
		private int empId;
		private String empName;

		@ManyToOne
		@JoinColumn(name="deptId_fk")
		private Department department;






	one-to-one mapping
	______________________

	In a one-to-one mapping the owner can be
	 either the source entity or the target entity.


				1		1
			Employee<>----------Parking





	public class Parking {
	
		private int partingId;
		private String parkingLocation;

	
		private Employee employee;



	
	public class Employee {
	
		private int empId;
		private String empName;

	
		private Parking parking;






	Now apply annotations
	---------------------------------



	@Entity
	public class Parking {
		@Id
		@GeneratedValue
		private int partingId;
		private String parkingLocation;

		@OneToOne(mappedBy = "parking")
		private Employee employee;



	@Entity
	public class Employee {
		@Id
		@GeneratedValue
		private int empId;
		private String empName;

		@OneToOne
		private Parking parking;







	Many-to-Many mapping
	______________________


	   N		      N
	Employee----<>----- Project




	Create POJO
	-------------

	public class Employee {
	
		private int empId;
		private String empName;
	
		private Collection<Project> projects = new ArrayList<Project>();





	public class Project {
	
		private int projectId;
		private String projectName;

		private Collection<Employee> employees = new ArrayList<Employee>();









	Apply annotations
	----------------
	@Entity
	public class Employee {
		@Id
		@GeneratedValue
		private int empId;
		private String empName;

		@ManyToMany
		private Collection<Project> projects = new ArrayList<Project>();




	@Entity
	public class Project {
		@Id
		@GeneratedValue
		private int projectId;
		private String projectName;

		@ManyToMany(mappedBy="projects")
		private Collection<Employee> employees = new ArrayList<Employee>();



	Note:
	----------

	3 tables is going to be created:

		Employee 

		Project 

		employee_project (join table)
		--------------------------
			employees_empId
			projects_projectId


		Want more control:




	@Entity
	public class Employee {
		@Id
		@GeneratedValue
		private int empId;
		private String empName;

		@ManyToMany
		@JoinTable(name="emp_project", joinColumns={@JoinColumn(name="e_id" , referencedColumnName="empId")},
			inverseJoinColumns={
				@JoinColumn(name="proj_d", referencedColumnName="projectId")})
	
		private Collection<Project> projects = new ArrayList<Project>();












Some more exampels:





One to one
-----------
	Unidirectional:
		1		1
	Employee -------------> Parking

	@Entity
	class Employee {

	@Id
	private int id;

	private Parking parking;

	......
	......

	@OneToOne(cascade={CascadeType=PERSIST})
	public Parking getParking{

	}


	}


	@Entity
	class Parking{

	}


Cascade type:
-------------
	CascadeType.ALL	cascade all operations
		MERGE
		PERSIST
		REFRESH
		REMOVE





One to Many
-----------
	Unidirectional:

	
		1		N
	Company -------------> Employee


	@Entity
	class Company{

		private int id;

		private Collection<Employee> employees;

		.....
		........
		@OneToMany(Cascade={CascadeType.ALL}, fetch=FechType.EAGER)
		public Collection <Employee> getEmployees(){

		}

	}

	@Entity
	class Employee{



	}


Many to One
-----------
	Unidirectional:


	
		1		N
	Employee -------------> BusinessAddress


	@Entity
	class Employee{

	@Id
	private int id;

	.....
	private BusinessAddress businessAddress;

	@ManyToOne(Cascade={CascadeType.ALL})
	//apply on getter

	}


	@Entity
	class BusinessAddress{

	@Id
	private int id;
	}



Many to Many
-----------
	Unidirectional:

	Book <---------------> Author

	3 tables;
	----------

	Book ----Book_Author------- Author



	@Entity
	class Book{

	@Id
	private int id;
	private String bookName;

	private Collection<Author> authors=new ArrayList<Author>();

	@ManyToMany(Cascade={CasCadeType.ALL}, fetch=FetchType.Eager)
	@JoinTable(name="Book_Author")

	public Collection<Author>getAuthors(){
	}
	}


	@Entity
	class Author{

	@Id
	private int id;
	private String authorName;

	private Collection<Book> books=new ArrayList<Book>();

	}

	Now:
	------

	Author a1=new Author();
	...... a2=.........

	Book b1=new Book();
	... b2=...........

	b1.getAuthors().add(a1);
	b1.getAuthors().add(a2);


	.......
	em.persist(a1);
	em.persist(a2);


	for(Book b: ba.getAllBooks){

		Sysout(b.getBookName());

		for(AUthor temp:b.getAuthors())	
			Sysout(temp.getAuthorName());
	

	}


Understandign JPQUL
--------------------

	Extended version of EJB QueryLanguage

	Operate on classes and Object (entities ) in java workspace
	while SQl works on row/column in DB space

	JPQL statement can be extended only after they converted to 
	SQL

	JPQL --------> JPQL ------------------> SQL
		   Query Processor		Quaries


	Ex:

	Select e.employeeName form Employee e;


	
Query API
-----------

	Allow to create custom quaries to access a single or a collection
	of entities from the database

	Dynamic Quaries
	--------------
	Query q=em.createQuery("select e from Employee e");


	Named Query
	-----------
	Enhanced performance of execution of code
	
	Improve maintanance / reusability of code

	created / stored by using meta data annotaitons

	@Entiy
	@NamedQuery(name="findAllEmployees" query="select e from Employee e where e.employeeName LIKE : 		employeeName")


	public class Employee {




	}


	
	Query 	q=em.createNamedQuery("findAllEmployees");
		q.setParameter("employeeName",'raja');

		paginations
		----------
		q.setMaxResult(10);
		q.setFirstResult(3);

		List emp=q.getResultList();




















Follow these simple steps to setup Database Connection Pooling inside Tomcat 6.x

Step 1

Add following element to conf/context.xml file. This Resource will be avaible to all Applications deployed

<Resource name="jdbc/SampleDb" auth="Container" type="javax.sql.DataSource"
maxActive="50" maxIdle="3" maxWait="10000"
username="" password=""
driverClassName=""
url=""/>

provide values for all attributes (username, password, driverClassName, url etc)

Step 2
Add following to web.xml of your Application

<resource-ref>
<description>DB Connection</description>
<res-ref-name>jdbc/SampleDB</res-ref-name>
<res-type>javax.sql.DataSource</res-type>
<res-auth>Container</res-auth>
</resource-ref>

Step 3
Refer JNDI Data source inside your persistence.xml

If you want to use JTA use this configuration

<persistence-unit name="SamplePU" transaction-type="JTA">
<jta-data-source>java:/comp/env/jdbc/SampleDB</jta-data-source>
..
</persistence-unit>

and for non JTA
<persistence-unit name="SamplePU" transaction-type="RESOURCE_LOCAL">
<non-jta-data-source>java:/comp/env/jdbc/SampleDB</non-jta-data-source>
..
</persistence-unit>




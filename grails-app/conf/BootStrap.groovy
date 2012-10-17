import com.mwu.*

class BootStrap {

    def init = { servletContext ->
		new Entries(name: "Kristina Chung").save()
		new Entries(name: "Paige Chen").save()
		new Entries(name: "Sherri Melton").save()
		new Entries(name: "Gretchen Hill").save()
		new Entries(name: "Karen Puckett").save()
		new Entries(name: "Patrick Song").save()
		new Entries(name: "Elsie Hamilton").save()
		new Entries(name: "Hazel Bender").save()
		new Entries(name: "Malcolm Wagner").save()
		new Entries(name: "Dolores McLaughlin").save()
		new Entries(name: "Francis McNamara").save()
		new Entries(name: "Sandy Raynor").save()
		new Entries(name: "Marion Moon").save()
		new Entries(name: "Beth Woodard").save()
		new Entries(name: "Julia Desai").save()
		new Entries(name: "Jerome Wallace").save()
		new Entries(name: "Neal Lawrence").save()
		new Entries(name: "Jean Griffin").save()
		new Entries(name: "Kristine Dougherty").save()
		new Entries(name: "Crystal Powers").save()
		new Entries(name: "Alex May").save()
		new Entries(name: "Eric Steele").save()
		new Entries(name: "Wesley Teague").save()
		new Entries(name: "Franklin Vick").save()
		new Entries(name: "Claire Gallagher").save()
		new Entries(name: "Marian Solomon").save()
		new Entries(name: "Marcia Walsh").save()
		new Entries(name: "Dwight Monroe").save()
		new Entries(name: "Wayne Connolly").save()
		new Entries(name: "Stephanie Hawkins").save()
    }
    def destroy = {
    }
}
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Genetic Algorithm Implementation</title>
<link rel="stylesheet" type="text/css" href="css/view.css" media="all" />
<script type="text/javascript" src="js/view.js"></script>
<script type="text/javascript" src="js/calendar.js"></script>
</head>
<body id="main_body">

<img id="top" src="images/top.png" alt="" />
<div id="form_container">


<h1><a>Genetic Algorithm Implementation</a></h1>
<form id="form_188143" class="appnitro" method="post" action="showResults">
<div class="form_description">
<h2>Genetic Algorithm Implementation</h2>
<p></p>
</div>
<ul>

	<li class="section_break">

	<h3><u>Input Function</u></h3>
	<p>Select one of the functions, and pick the Maximization or the
	Minimization problem</p>
	</li>
	<li id="li_6"><label class="description" for="element_6">Function
	f(xi) </label>
	<div><select class="element select large" id="" name="function">
		<optgroup label="Function 1">
			<option value="1" selected="selected">Max{f1}: f(x) = x*sin(10*PI*x) + 1.0; x in [-1.00,2.00]</option>
			<option value="4">Min{f1}: f(x) = x*sin(10*PI*x) + 1.0; x in [-1.00,2.00]</option>
		</optgroup>
		<optgroup label="Function 2">
			<option value="2">Max{f2}:
			418.9829*2–x1*sin(sqrt(abs(x1)))–x2*sin(sqrt(abs(x2)));</option>
			<option value="5">Min{f2}:
			418.9829*2–x1*sin(sqrt(abs(x1)))–x2*sin(sqrt(abs(x2)));</option>
		</optgroup>
		<optgroup label="Function 3">
			<option value="3">Max{f3}:
			1/4000*(x1^2+x2^2)-cos(x1)*cos(x2/sqrt(2))+1;</option>
			<option value="6">Min{f3}:
			1/4000*(x1^2+x2^2)-cos(x1)*cos(x2/sqrt(2))+1;</option>
		</optgroup>

	</select></div>
	<p class="guidelines" id="guide_6"><small>Select one of the
	functions, and view the results one at a time</small></p>
	</li>
	<li class="section_break">

	<h3><u>Genetic Algorithm Parameters</u></h3>
	<p></p>
	</li>
	<li id="li_9"><label class="description" for="element_9">Population
	Size </label>
	<div><select class="element select small" id="popSize"
		name="popSize">
		<option value="10">10</option>

		<option value="20">20</option>
		<option value="30">30</option>
		<option value="40" selected="selected">40</option>
		<option value="50">50</option>
		<option value="60">60</option>
		<option value="70">70</option>
		<option value="80">80</option>
		<option value="90">90</option>
		<option value="100">100</option>

	</select></div>
	<p class="guidelines" id="guide_9"><small>Enter the
	population size of the chromosomes for one single generation</small></p>
	</li>
	<li id="li_7"><label class="description" for="element_7">Precision
	</label>
	<div><input id="element_7" name="precision"
		class="element text small" type="text" maxlength="255" value="" /></div>
	<p class="guidelines" id="guide_7"><small>Enter the
	precision as digits that are to be visible after the decimal point</small></p>
	</li>
	<li id="li_2"><label class="description" for="element_2">Cross
	Over Probability </label>
	<div><input id="element_2" name="crossOverProbability"
		class="element text small" type="text" maxlength="255" value="" /></div>
	<p class="guidelines" id="guide_2"><small>Enter the cross
	over probability for the evolution</small></p>
	</li>
	<li id="li_3"><label class="description" for="element_3">Mutation
	Probability </label>
	<div><input id="element_3" name="mutationProbability"
		class="element text small" type="text" maxlength="255" value="" /></div>
	<p class="guidelines" id="guide_3"><small>Enter the
	mutation probability for the evolution</small></p>
	</li>
	<li id="li_5"><label class="description" for="element_5">Max.
	Generations in the Evolution </label>
	<div><input id="element_5" name="maxGenerations"
		class="element text small" type="text" maxlength="255" value="" /></div>
	<p class="guidelines" id="guide_5"><small>Enter the maximum
	generations that are permissible in the evolution</small></p>
	</li>

	<li class="buttons"><input type="hidden" name="form_id"
		value="188143" /> <input id="saveForm" class="button_text"
		type="submit" name="submit" value="Submit" /></li>

</ul>
</form>
<div id="footer">Submitted by <a
	href="mailto:beechase@email.arizona.edu">Chandrasekhar Manda</a></div>
</div>
<img id="bottom" src="images/bottom.png" alt="" />
</body>

</html>
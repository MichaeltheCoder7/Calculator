#!/usr/bin/env python3
"""
Comprehensive Test Suite for Blue Theme Calculator Application
Tests both Simple Calculator and Scientific Calculator functionality
"""

import subprocess
import sys
import os
from datetime import datetime

class CalculatorTester:
    def __init__(self):
        self.tests_run = 0
        self.tests_passed = 0
        self.compilation_success = False
        
    def log_test(self, test_name, success, details=""):
        """Log test results"""
        self.tests_run += 1
        if success:
            self.tests_passed += 1
            print(f"✅ {test_name}: PASSED {details}")
        else:
            print(f"❌ {test_name}: FAILED {details}")
        return success

    def test_compilation(self):
        """Test if all Java files compile successfully"""
        print("\n🔍 Testing Java Compilation...")
        try:
            # Change to app directory and compile
            result = subprocess.run(
                ["javac", "src/calculator/*.java"],
                cwd="/app",
                capture_output=True,
                text=True,
                shell=True
            )
            
            # Check if class files were created
            class_files_exist = True
            expected_classes = [
                "/app/src/calculator/Calculator.class",
                "/app/src/calculator/CalculatorForm.class", 
                "/app/src/calculator/CalculatorInterface.class",
                "/app/src/calculator/ExprCalculator.class",
                "/app/src/calculator/Main.class"
            ]
            
            missing_files = []
            for class_file in expected_classes:
                if not os.path.exists(class_file):
                    class_files_exist = False
                    missing_files.append(class_file)
            
            success = result.returncode == 0 and class_files_exist
            if success:
                self.compilation_success = True
                details = "All Java files compiled successfully"
            else:
                if missing_files:
                    details = f"Missing class files: {missing_files}"
                else:
                    details = f"Compilation issues: {result.stderr if result.stderr else 'Unknown error'}"
                
            return self.log_test("Java Compilation", success, details)
            
        except Exception as e:
            return self.log_test("Java Compilation", False, f"Exception: {str(e)}")

    def run_java_test(self, class_name, expected_outputs=None):
        """Run a Java class and check its output"""
        try:
            result = subprocess.run(
                ["java", "-cp", ".", f"calculator.{class_name}"],
                cwd="/app",
                capture_output=True,
                text=True,
                timeout=10
            )
            
            if result.returncode != 0:
                return False, f"Runtime error: {result.stderr}"
                
            output = result.stdout.strip()
            if expected_outputs:
                for expected in expected_outputs:
                    if expected not in output:
                        return False, f"Expected '{expected}' not found in output: {output}"
            
            return True, output
            
        except subprocess.TimeoutExpired:
            return False, "Test timed out"
        except Exception as e:
            return False, f"Exception: {str(e)}"

    def test_simple_calculator_logic(self):
        """Test simple calculator functionality by creating a custom test"""
        print("\n🔍 Testing Simple Calculator Logic...")
        
        # Create a test file for simple calculator
        test_code = '''
package calculator;

public class SimpleCalculatorTest {
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        
        // Test basic addition
        calc.acceptInput("5");
        calc.acceptInput("+");
        calc.acceptInput("3");
        calc.acceptInput("=");
        System.out.println("5+3=" + calc.getDisplayString());
        
        // Test clear
        calc.acceptInput("C");
        System.out.println("After clear: " + calc.getDisplayString());
        
        // Test multiplication
        calc.acceptInput("4");
        calc.acceptInput("*");
        calc.acceptInput("6");
        calc.acceptInput("=");
        System.out.println("4*6=" + calc.getDisplayString());
        
        // Test division
        calc.acceptInput("C");
        calc.acceptInput("8");
        calc.acceptInput("/");
        calc.acceptInput("2");
        calc.acceptInput("=");
        System.out.println("8/2=" + calc.getDisplayString());
        
        // Test division by zero
        calc.acceptInput("C");
        calc.acceptInput("5");
        calc.acceptInput("/");
        calc.acceptInput("0");
        calc.acceptInput("=");
        System.out.println("5/0=" + calc.getDisplayString());
        
        // Test decimal numbers
        calc.acceptInput("C");
        calc.acceptInput("3");
        calc.acceptInput(".");
        calc.acceptInput("5");
        calc.acceptInput("+");
        calc.acceptInput("2");
        calc.acceptInput(".");
        calc.acceptInput("1");
        calc.acceptInput("=");
        System.out.println("3.5+2.1=" + calc.getDisplayString());
        
        // Test error handling - multiple operators
        calc.acceptInput("C");
        calc.acceptInput("5");
        calc.acceptInput("+");
        calc.acceptInput("*");
        System.out.println("5+* error: " + calc.getDisplayString());
    }
}
'''
        
        # Write test file
        with open("/app/src/calculator/SimpleCalculatorTest.java", "w") as f:
            f.write(test_code)
        
        # Compile test
        try:
            result = subprocess.run(
                ["javac", "src/calculator/SimpleCalculatorTest.java"],
                cwd="/app",
                capture_output=True,
                text=True,
                shell=True
            )
            
            if result.returncode != 0:
                return self.log_test("Simple Calculator Compilation", False, result.stderr)
            
            # Run test
            success, output = self.run_java_test("SimpleCalculatorTest")
            
            if success:
                # Check expected results
                expected_results = [
                    "5+3=8.0",
                    "After clear: 0",
                    "4*6=24.0", 
                    "8/2=4.0",
                    "5/0=NaN",
                    "3.5+2.1=5.6",
                    "5+* error: Error"
                ]
                
                all_found = True
                missing = []
                for expected in expected_results:
                    if expected not in output:
                        all_found = False
                        missing.append(expected)
                
                if all_found:
                    return self.log_test("Simple Calculator Logic", True, "All basic operations work correctly")
                else:
                    return self.log_test("Simple Calculator Logic", False, f"Missing results: {missing}")
            else:
                return self.log_test("Simple Calculator Logic", False, output)
                
        except Exception as e:
            return self.log_test("Simple Calculator Logic", False, f"Exception: {str(e)}")

    def test_scientific_calculator_logic(self):
        """Test scientific calculator functionality"""
        print("\n🔍 Testing Scientific Calculator Logic...")
        
        test_code = '''
package calculator;

public class ScientificCalculatorTest {
    public static void main(String[] args) {
        ExprCalculator calc = new ExprCalculator();
        
        // Test basic arithmetic
        calc.acceptInput("5+3*2");
        calc.acceptInput("=");
        System.out.println("5+3*2=" + calc.getDisplayString());
        
        // Test parentheses
        calc.acceptInput("C");
        calc.acceptInput("(5+3)*2");
        calc.acceptInput("=");
        System.out.println("(5+3)*2=" + calc.getDisplayString());
        
        // Test square root
        calc.acceptInput("C");
        calc.acceptInput("sqrt16");
        calc.acceptInput("=");
        System.out.println("sqrt16=" + calc.getDisplayString());
        
        // Test sine (approximately)
        calc.acceptInput("C");
        calc.acceptInput("sin0");
        calc.acceptInput("=");
        System.out.println("sin0=" + calc.getDisplayString());
        
        // Test factorial
        calc.acceptInput("C");
        calc.acceptInput("fac5");
        calc.acceptInput("=");
        System.out.println("fac5=" + calc.getDisplayString());
        
        // Test logarithm
        calc.acceptInput("C");
        calc.acceptInput("log100");
        calc.acceptInput("=");
        System.out.println("log100=" + calc.getDisplayString());
        
        // Test power
        calc.acceptInput("C");
        calc.acceptInput("2^3");
        calc.acceptInput("=");
        System.out.println("2^3=" + calc.getDisplayString());
        
        // Test modulo
        calc.acceptInput("C");
        calc.acceptInput("10mod3");
        calc.acceptInput("=");
        System.out.println("10mod3=" + calc.getDisplayString());
        
        // Test error - mismatched parentheses
        calc.acceptInput("C");
        calc.acceptInput("(5+3");
        calc.acceptInput("=");
        System.out.println("(5+3 error: " + calc.getDisplayString());
        
        // Test backspace
        calc.acceptInput("C");
        calc.acceptInput("123");
        calc.acceptInput("Backspace");
        System.out.println("123 backspace: " + calc.getDisplayString());
    }
}
'''
        
        # Write test file
        with open("/app/src/calculator/ScientificCalculatorTest.java", "w") as f:
            f.write(test_code)
        
        # Compile test
        try:
            result = subprocess.run(
                ["javac", "src/calculator/ScientificCalculatorTest.java"],
                cwd="/app",
                capture_output=True,
                text=True,
                shell=True
            )
            
            if result.returncode != 0:
                return self.log_test("Scientific Calculator Compilation", False, result.stderr)
            
            # Run test
            success, output = self.run_java_test("ScientificCalculatorTest")
            
            if success:
                # Check some expected results (allowing for floating point precision)
                checks = [
                    ("5+3*2=11", "Order of operations"),
                    ("(5+3)*2=16", "Parentheses"),
                    ("sqrt16=4", "Square root"),
                    ("sin0=0", "Sine function"),
                    ("fac5=120", "Factorial"),
                    ("log100=2", "Logarithm"),
                    ("2^3=8", "Power"),
                    ("10mod3=1", "Modulo"),
                    ("Error:Parentheses", "Error handling"),
                    ("123 backspace: 12", "Backspace")
                ]
                
                passed_checks = 0
                for expected, description in checks:
                    if expected in output:
                        passed_checks += 1
                        print(f"  ✓ {description}")
                    else:
                        print(f"  ✗ {description} - Expected: {expected}")
                
                success_rate = passed_checks / len(checks)
                if success_rate >= 0.8:  # 80% pass rate
                    return self.log_test("Scientific Calculator Logic", True, 
                                       f"{passed_checks}/{len(checks)} checks passed")
                else:
                    return self.log_test("Scientific Calculator Logic", False, 
                                       f"Only {passed_checks}/{len(checks)} checks passed")
            else:
                return self.log_test("Scientific Calculator Logic", False, output)
                
        except Exception as e:
            return self.log_test("Scientific Calculator Logic", False, f"Exception: {str(e)}")

    def test_ui_styling_implementation(self):
        """Test that UI styling code is properly implemented"""
        print("\n🔍 Testing UI Styling Implementation...")
        
        try:
            with open("/app/src/calculator/CalculatorForm.java", "r") as f:
                content = f.read()
            
            # Check for blue theme color constants
            color_checks = [
                ("PRIMARY_BLUE = new Color(33, 150, 243)", "Primary blue color"),
                ("DARK_BLUE = new Color(25, 118, 210)", "Dark blue color"),
                ("LIGHT_BLUE = new Color(227, 242, 253)", "Light blue color"),
                ("NAVY_BLUE = new Color(13, 71, 161)", "Navy blue color"),
                ("BUTTON_HOVER = new Color(100, 181, 246)", "Button hover color")
            ]
            
            styling_checks = [
                ("setBackground(PRIMARY_BLUE)", "Primary blue background"),
                ("setBackground(LIGHT_BLUE)", "Light blue background"),
                ("setForeground(WHITE)", "White foreground"),
                ("setForeground(NAVY_BLUE)", "Navy blue foreground"),
                ("mouseEntered", "Hover effect implementation"),
                ("mouseExited", "Hover effect implementation"),
                ("BorderFactory.createLineBorder(PRIMARY_BLUE, 3)", "Blue border"),
                ("Font(\"SansSerif\", Font.BOLD", "Bold font styling"),
                ("setCursor(new Cursor(Cursor.HAND_CURSOR))", "Hand cursor"),
                ("setFocusPainted(false)", "Focus paint disabled")
            ]
            
            ui_structure_checks = [
                ("JTabbedPane", "Tabbed interface"),
                ("Simple Calculator", "Simple calculator tab"),
                ("Scientific Calculator", "Scientific calculator tab"),
                ("JRadioButton", "Radio button mode switching"),
                ("GridLayout", "Grid layout for buttons"),
                ("BorderLayout", "Border layout"),
                ("EmptyBorder", "Padding implementation")
            ]
            
            all_checks = color_checks + styling_checks + ui_structure_checks
            passed = 0
            
            for check, description in all_checks:
                if check in content:
                    passed += 1
                    print(f"  ✓ {description}")
                else:
                    print(f"  ✗ {description}")
            
            success_rate = passed / len(all_checks)
            if success_rate >= 0.9:  # 90% pass rate for styling
                return self.log_test("UI Styling Implementation", True, 
                                   f"{passed}/{len(all_checks)} styling elements found")
            else:
                return self.log_test("UI Styling Implementation", False, 
                                   f"Only {passed}/{len(all_checks)} styling elements found")
                
        except Exception as e:
            return self.log_test("UI Styling Implementation", False, f"Exception: {str(e)}")

    def test_existing_main_class(self):
        """Test the existing Main class functionality"""
        print("\n🔍 Testing Existing Main Class...")
        
        expected_outputs = [
            "18*179-98/2",
            "Display: 3173",
            "Display: 3173.",
            "Display: 3173..9",
            "Display: Error"
        ]
        
        success, output = self.run_java_test("Main", expected_outputs)
        return self.log_test("Existing Main Class", success, output if not success else "All expected outputs found")

    def test_interface_implementation(self):
        """Test that both calculators properly implement the interface"""
        print("\n🔍 Testing Interface Implementation...")
        
        test_code = '''
package calculator;

public class InterfaceTest {
    public static void main(String[] args) {
        // Test that both classes implement the interface
        CalculatorInterface simple = new Calculator();
        CalculatorInterface scientific = new ExprCalculator();
        
        // Test simple calculator through interface
        simple.acceptInput("2");
        simple.acceptInput("+");
        simple.acceptInput("3");
        simple.acceptInput("=");
        System.out.println("Simple via interface: " + simple.getDisplayString());
        
        // Test scientific calculator through interface
        scientific.acceptInput("2*3+1");
        scientific.acceptInput("=");
        System.out.println("Scientific via interface: " + scientific.getDisplayString());
        
        System.out.println("Interface implementation: SUCCESS");
    }
}
'''
        
        # Write test file
        with open("/app/src/calculator/InterfaceTest.java", "w") as f:
            f.write(test_code)
        
        try:
            # Compile test
            result = subprocess.run(
                ["javac", "src/calculator/InterfaceTest.java"],
                cwd="/app",
                capture_output=True,
                text=True,
                shell=True
            )
            
            if result.returncode != 0:
                return self.log_test("Interface Test Compilation", False, result.stderr)
            
            # Run test
            success, output = self.run_java_test("InterfaceTest")
            
            if success and "Interface implementation: SUCCESS" in output:
                return self.log_test("Interface Implementation", True, "Both calculators implement interface correctly")
            else:
                return self.log_test("Interface Implementation", False, output)
                
        except Exception as e:
            return self.log_test("Interface Implementation", False, f"Exception: {str(e)}")

    def run_all_tests(self):
        """Run all tests and provide summary"""
        print("🧮 Blue Theme Calculator Test Suite")
        print("=" * 50)
        
        # Test compilation first
        if not self.test_compilation():
            print("\n❌ Compilation failed. Cannot proceed with functionality tests.")
            return False
        
        # Run all functionality tests
        self.test_existing_main_class()
        self.test_simple_calculator_logic()
        self.test_scientific_calculator_logic()
        self.test_interface_implementation()
        self.test_ui_styling_implementation()
        
        # Print summary
        print("\n" + "=" * 50)
        print(f"📊 TEST SUMMARY")
        print(f"Tests Run: {self.tests_run}")
        print(f"Tests Passed: {self.tests_passed}")
        print(f"Success Rate: {(self.tests_passed/self.tests_run)*100:.1f}%")
        
        if self.tests_passed == self.tests_run:
            print("🎉 ALL TESTS PASSED!")
            return True
        elif self.tests_passed / self.tests_run >= 0.8:
            print("✅ Most tests passed - Application is functional")
            return True
        else:
            print("⚠️  Several tests failed - Application needs attention")
            return False

def main():
    """Main test execution"""
    tester = CalculatorTester()
    success = tester.run_all_tests()
    return 0 if success else 1

if __name__ == "__main__":
    sys.exit(main())
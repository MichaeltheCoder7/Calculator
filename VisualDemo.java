public class VisualDemo {
    public static void main(String[] args) {
        System.out.println("=== BLUE THEME CALCULATOR VISUAL REPRESENTATION ===\n");
        
        // ANSI color codes for terminal display
        String BLUE = "\u001B[44m";
        String LIGHT_BLUE = "\u001B[46m";
        String DARK_BLUE = "\u001B[44m";
        String WHITE = "\u001B[47m";
        String GRAY = "\u001B[47m";
        String RESET = "\u001B[0m";
        
        System.out.println("🎨 SIMPLE CALCULATOR TAB:");
        System.out.println("┌─────────────────────────────────────────────┐");
        System.out.println("│           📱 Blue Theme Calculator          │");
        System.out.println("├─────────────────────────────────────────────┤");
        System.out.println("│  [Simple Calculator] [Scientific Calculator] │");
        System.out.println("├─────────────────────────────────────────────┤");
        System.out.println("│                                             │");
        System.out.println("│     ┌─────────────────────────────────┐     │");
        System.out.println("│     │                               0 │ ← Display (blue border)");
        System.out.println("│     └─────────────────────────────────┘     │");
        System.out.println("│                                             │");
        System.out.println("│    ┌─────┐ ┌─────┐ ┌─────┐ ┌─────┐        │");
        System.out.println("│    │  C  │ │  /  │ │     │ │     │        │");
        System.out.println("│    └─────┘ └─────┘ └─────┘ └─────┘        │");
        System.out.println("│                                             │");
        System.out.println("│    ┌─────┐ ┌─────┐ ┌─────┐ ┌─────┐        │");
        System.out.println("│    │  7  │ │  8  │ │  9  │ │  *  │        │");
        System.out.println("│    └─────┘ └─────┘ └─────┘ └─────┘        │");
        System.out.println("│                                             │");
        System.out.println("│    ┌─────┐ ┌─────┐ ┌─────┐ ┌─────┐        │");
        System.out.println("│    │  4  │ │  5  │ │  6  │ │  -  │        │");
        System.out.println("│    └─────┘ └─────┘ └─────┘ └─────┘        │");
        System.out.println("│                                             │");
        System.out.println("│    ┌─────┐ ┌─────┐ ┌─────┐ ┌─────┐        │");
        System.out.println("│    │  1  │ │  2  │ │  3  │ │  +  │        │");
        System.out.println("│    └─────┘ └─────┘ └─────┘ └─────┘        │");
        System.out.println("│                                             │");
        System.out.println("│    ┌─────┐ ┌─────┐ ┌─────┐ ┌─────┐        │");
        System.out.println("│    │  0  │ │  .  │ │  =  │ │     │        │");
        System.out.println("│    └─────┘ └─────┘ └─────┘ └─────┘        │");
        System.out.println("│                                             │");
        System.out.println("│        ○ Simple    ○ Scientific             │");
        System.out.println("└─────────────────────────────────────────────┘");
        
        System.out.println("\n🔬 SCIENTIFIC CALCULATOR TAB:");
        System.out.println("┌─────────────────────────────────────────────────────────┐");
        System.out.println("│               📱 Blue Theme Calculator                  │");
        System.out.println("├─────────────────────────────────────────────────────────┤");
        System.out.println("│    [Simple Calculator] [Scientific Calculator]          │");
        System.out.println("├─────────────────────────────────────────────────────────┤");
        System.out.println("│                                                         │");
        System.out.println("│         ┌─────────────────────────────────────┐         │");
        System.out.println("│         │                                   0 │ ← Display");
        System.out.println("│         └─────────────────────────────────────┘         │");
        System.out.println("│                                                         │");
        System.out.println("│  ┌─────┐ ┌─────┐ ┌─────┐ ┌─────┐ ┌─────┐             │");
        System.out.println("│  │ sin │ │ cos │ │ tan │ │  C  │ │ Del │             │");
        System.out.println("│  └─────┘ └─────┘ └─────┘ └─────┘ └─────┘             │");
        System.out.println("│                                                         │");
        System.out.println("│  ┌─────┐ ┌─────┐ ┌─────┐ ┌─────┐ ┌─────┐             │");
        System.out.println("│  │ log │ │ ln  │ │sqrt │ │  (  │ │  )  │             │");
        System.out.println("│  └─────┘ └─────┘ └─────┘ └─────┘ └─────┘             │");
        System.out.println("│                                                         │");
        System.out.println("│  ┌─────┐ ┌─────┐ ┌─────┐ ┌─────┐ ┌─────┐             │");
        System.out.println("│  │ fac │ │ mod │ │  ^  │ │  /  │ │  *  │             │");
        System.out.println("│  └─────┘ └─────┘ └─────┘ └─────┘ └─────┘             │");
        System.out.println("│                                                         │");
        System.out.println("│  ┌─────┐ ┌─────┐ ┌─────┐ ┌─────┐ ┌─────┐             │");
        System.out.println("│  │  7  │ │  8  │ │  9  │ │  -  │ │     │             │");
        System.out.println("│  └─────┘ └─────┘ └─────┘ └─────┘ └─────┘             │");
        System.out.println("│                                                         │");
        System.out.println("│  ┌─────┐ ┌─────┐ ┌─────┐ ┌─────┐ ┌─────┐             │");
        System.out.println("│  │  4  │ │  5  │ │  6  │ │  +  │ │     │             │");
        System.out.println("│  └─────┘ └─────┘ └─────┘ └─────┘ └─────┘             │");
        System.out.println("│                                                         │");
        System.out.println("│  ┌─────┐ ┌─────┐ ┌─────┐ ┌─────┐ ┌─────┐             │");
        System.out.println("│  │  1  │ │  2  │ │  3  │ │     │ │  =  │             │");
        System.out.println("│  └─────┘ └─────┘ └─────┘ └─────┘ └─────┘             │");
        System.out.println("│                                                         │");
        System.out.println("│  ┌─────┐ ┌─────┐ ┌─────┐ ┌─────┐ ┌─────┐             │");
        System.out.println("│  │  0  │ │  .  │ │     │ │     │ │     │             │");
        System.out.println("│  └─────┘ └─────┘ └─────┘ └─────┘ └─────┘             │");
        System.out.println("│                                                         │");
        System.out.println("│            ● Scientific    ○ Simple                    │");
        System.out.println("└─────────────────────────────────────────────────────────┘");
        
        System.out.println("\n🎨 COLOR SCHEME LEGEND:");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("🔵 Primary Blue (#2196F3)    - Operators (+, -, *, /) & Functions");
        System.out.println("🔷 Dark Blue (#1976D2)       - Clear (C), Delete, Equals (=)");
        System.out.println("💙 Light Blue (#E3F2FD)      - Background & Panel");
        System.out.println("🌐 Navy Blue (#0D47A1)       - Text & Borders");
        System.out.println("⚪ Light Gray (#F5F5F5)      - Number buttons (0-9, .)");
        System.out.println("✨ Button Hover (#64B5F6)    - Hover effect color");
        
        System.out.println("\n✨ INTERACTIVE FEATURES:");
        System.out.println("→ Buttons glow with light blue on hover");
        System.out.println("→ Hand cursor appears on button hover");
        System.out.println("→ Display has thick blue border with padding");
        System.out.println("→ Monospaced font for perfect number alignment");
        System.out.println("→ Smooth transitions and professional spacing");
        System.out.println("→ Tab switching between Simple and Scientific modes");
        
        System.out.println("\n🚀 RESULT: A modern, professional calculator with");
        System.out.println("   a beautiful blue aesthetic that's both functional");
        System.out.println("   and visually appealing!");
    }
}
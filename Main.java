import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private JFrame frame;
    private JTextArea terminalArea;
    private JPanel buttonPanel;
    private JPanel menuPanel;
    private CardLayout cardLayout;

    // POS Data
    private List<Item> items = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();
    private List<Sale> sales = new ArrayList<>();

    // Color scheme
    private final Color bgDark = new Color(30, 30, 30);
    private final Color bgLight = new Color(45, 45, 45);
    private final Color primaryColor = new Color(29, 205, 159);
    private final Color secondaryColor = new Color(22, 153, 118);
    private final Color textLight = Color.WHITE;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main().createAndShowGUI();
        });
    }

    private void createAndShowGUI() {
        initializeSampleData();

        // Main window setup
        frame = new JFrame("Advanced POS System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setLayout(new BorderLayout(10, 10));
        frame.getContentPane().setBackground(bgLight);

        // Create dashboard panel
        JPanel dashboardPanel = new JPanel(new BorderLayout());
        dashboardPanel.setBackground(bgLight);
        dashboardPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create horizontal button panel for main navigation
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        buttonPanel.setBackground(bgLight);

        // Create main menu buttons
        JButton dashboardBtn = createStyledButton("Dashboard", primaryColor);
        JButton itemsBtn = createStyledButton("Manage Items", secondaryColor);
        JButton customersBtn = createStyledButton("Manage Customers", primaryColor);
        JButton salesBtn = createStyledButton("Make New Sale", secondaryColor);
        JButton paymentsBtn = createStyledButton("Make Payment", primaryColor);
        JButton reportsBtn = createStyledButton("Print Reports", secondaryColor);
        JButton exitBtn = createStyledButton("Exit", new Color(205, 29, 41));

        // Add action listeners
        dashboardBtn.addActionListener(e -> showDashboard());
        itemsBtn.addActionListener(e -> showItemsMenu());
        customersBtn.addActionListener(e -> showCustomersMenu());
        salesBtn.addActionListener(e -> showSalesMenu());
        paymentsBtn.addActionListener(e -> showPaymentsMenu());
        reportsBtn.addActionListener(e -> showReportsMenu());
        exitBtn.addActionListener(e -> System.exit(0));

        // Add buttons to panel
        buttonPanel.add(dashboardBtn);
        buttonPanel.add(itemsBtn);
        buttonPanel.add(customersBtn);
        buttonPanel.add(salesBtn);
        buttonPanel.add(paymentsBtn);
        buttonPanel.add(reportsBtn);
        buttonPanel.add(exitBtn);

        // Terminal display area
        terminalArea = new JTextArea();
        terminalArea.setEditable(false);
        terminalArea.setBackground(bgDark);
        terminalArea.setForeground(primaryColor);
        terminalArea.setFont(new Font("Courier New", Font.PLAIN, 14));
        terminalArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(terminalArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(primaryColor, 1));

        // Menu panel with CardLayout
        menuPanel = new JPanel();
        cardLayout = new CardLayout();
        menuPanel.setLayout(cardLayout);
        menuPanel.setBackground(bgLight);

        // Create all menu panels
        createMenuPanels();

        // Add components to dashboard
        dashboardPanel.add(buttonPanel, BorderLayout.NORTH);
        dashboardPanel.add(menuPanel, BorderLayout.CENTER);

        // Add to main frame
        frame.add(dashboardPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Show initial dashboard
        showDashboard();

        frame.setVisible(true);
    }

    private void createMenuPanels() {
        // Dashboard panel
        JPanel dashboardMenu = new JPanel();
        dashboardMenu.setBackground(bgLight);
        menuPanel.add(dashboardMenu, "dashboard");

        // Items menu panel
        JPanel itemsMenu = new JPanel(new GridLayout(0, 1, 5, 5));
        itemsMenu.setBackground(bgLight);

        JButton addItemBtn = createStyledButton("1. Add New Item", primaryColor);
        JButton updateItemBtn = createStyledButton("2. Update Item Details", secondaryColor);
        JButton findItemBtn = createStyledButton("3. Find Items", primaryColor);
        JButton removeItemBtn = createStyledButton("4. Remove Existing Item", secondaryColor);
        JButton backFromItemsBtn = createStyledButton("5. Back to Main Menu", new Color(205, 29, 41));

        addItemBtn.addActionListener(e -> addItem());
        updateItemBtn.addActionListener(e -> updateItem());
        findItemBtn.addActionListener(e -> findItem());
        removeItemBtn.addActionListener(e -> removeItem());
        backFromItemsBtn.addActionListener(e -> showDashboard());

        itemsMenu.add(addItemBtn);
        itemsMenu.add(updateItemBtn);
        itemsMenu.add(findItemBtn);
        itemsMenu.add(removeItemBtn);
        itemsMenu.add(backFromItemsBtn);
        menuPanel.add(itemsMenu, "itemsMenu");

        // Customers menu panel
        JPanel customersMenu = new JPanel(new GridLayout(0, 1, 5, 5));
        customersMenu.setBackground(bgLight);

        JButton addCustomerBtn = createStyledButton("1. Add New Customer", primaryColor);
        JButton updateCustomerBtn = createStyledButton("2. Update Customer Details", secondaryColor);
        JButton findCustomerBtn = createStyledButton("3. Find Customer", primaryColor);
        JButton removeCustomerBtn = createStyledButton("4. Remove Existing Customer", secondaryColor);
        JButton backFromCustomersBtn = createStyledButton("5. Back to Main Menu", new Color(205, 29, 41));

        addCustomerBtn.addActionListener(e -> addCustomer());
        updateCustomerBtn.addActionListener(e -> updateCustomer());
        findCustomerBtn.addActionListener(e -> findCustomer());
        removeCustomerBtn.addActionListener(e -> removeCustomer());
        backFromCustomersBtn.addActionListener(e -> showDashboard());

        customersMenu.add(addCustomerBtn);
        customersMenu.add(updateCustomerBtn);
        customersMenu.add(findCustomerBtn);
        customersMenu.add(removeCustomerBtn);
        customersMenu.add(backFromCustomersBtn);
        menuPanel.add(customersMenu, "customersMenu");

        // Reports menu panel
        JPanel reportsMenu = new JPanel(new GridLayout(0, 1, 5, 5));
        reportsMenu.setBackground(bgLight);

        JButton stockBtn = createStyledButton("1. Stock in Hand", primaryColor);
        JButton ordersBtn = createStyledButton("2. Outstanding Orders", secondaryColor);
        JButton balanceBtn = createStyledButton("3. Customer Balance", primaryColor);
        JButton receiptsBtn = createStyledButton("4. Sales Receipts", secondaryColor);
        JButton backFromReportsBtn = createStyledButton("5. Back to Main Menu", new Color(205, 29, 41));

        stockBtn.addActionListener(e -> showStockReport());
        ordersBtn.addActionListener(e -> showOrdersReport());
        balanceBtn.addActionListener(e -> showBalanceReport());
        receiptsBtn.addActionListener(e -> showReceiptsReport());
        backFromReportsBtn.addActionListener(e -> showDashboard());

        reportsMenu.add(stockBtn);
        reportsMenu.add(ordersBtn);
        reportsMenu.add(balanceBtn);
        reportsMenu.add(receiptsBtn);
        reportsMenu.add(backFromReportsBtn);
        menuPanel.add(reportsMenu, "reportsMenu");
    }

    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(textLight);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(bgColor.darker(), 2),
                BorderFactory.createEmptyBorder(8, 15, 8, 15)
        ));
        button.setFocusPainted(false);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });

        return button;
    }

    private void initializeSampleData() {
        // Sample items
        items.add(new Item("Laptop", 999.99, 10));
        items.add(new Item("Smartphone", 699.99, 15));
        items.add(new Item("Headphones", 149.99, 25));

        // Sample customers
        customers.add(new Customer("John Doe", "john@example.com", "123 Main St"));
        customers.add(new Customer("Jane Smith", "jane@example.com", "456 Oak Ave"));
    }

    // Navigation methods
    private void showDashboard() {
        cardLayout.show(menuPanel, "dashboard");
        terminalArea.setText("=== POS SYSTEM DASHBOARD ===\n\n");
        terminalArea.append("System Status:\n");
        terminalArea.append("- Items in inventory: " + items.size() + "\n");
        terminalArea.append("- Registered customers: " + customers.size() + "\n");
        terminalArea.append("- Total sales: " + sales.size() + "\n\n");
        terminalArea.append("Use the buttons above to navigate the system.");
    }

    private void showItemsMenu() {
        cardLayout.show(menuPanel, "itemsMenu");
        terminalArea.setText("=== ITEMS MANAGEMENT ===\n\n");
        terminalArea.append("1. Add New Item\n");
        terminalArea.append("2. Update Item Details\n");
        terminalArea.append("3. Find Items\n");
        terminalArea.append("4. Remove Existing Item\n");
        terminalArea.append("5. Back to Main Menu\n\n");
    }

    private void showCustomersMenu() {
        cardLayout.show(menuPanel, "customersMenu");
        terminalArea.setText("=== CUSTOMERS MANAGEMENT ===\n\n");
        terminalArea.append("1. Add New Customer\n");
        terminalArea.append("2. Update Customer Details\n");
        terminalArea.append("3. Find Customer\n");
        terminalArea.append("4. Remove Existing Customer\n");
        terminalArea.append("5. Back to Main Menu\n\n");
    }

    private void showSalesMenu() {
        terminalArea.setText("=== NEW SALE ===\n\n");
        // Implementation would go here
        terminalArea.append("Sale functionality will be implemented here.\n");
    }

    private void showPaymentsMenu() {
        terminalArea.setText("=== PAYMENTS ===\n\n");
        // Implementation would go here
        terminalArea.append("Payment functionality will be implemented here.\n");
    }

    private void showReportsMenu() {
        cardLayout.show(menuPanel, "reportsMenu");
        terminalArea.setText("=== REPORTS ===\n\n");
        terminalArea.append("1. Stock in Hand\n");
        terminalArea.append("2. Outstanding Orders\n");
        terminalArea.append("3. Customer Balance\n");
        terminalArea.append("4. Sales Receipts\n");
        terminalArea.append("5. Back to Main Menu\n\n");
    }

    // Item management methods
    private void addItem() {
        terminalArea.append("\n=== ADD NEW ITEM ===\n");
        // Implementation would go here
        terminalArea.append("Item addition form would appear here.\n");
    }

    private void updateItem() {
        terminalArea.append("\n=== UPDATE ITEM ===\n");
        // Implementation would go here
        terminalArea.append("Item update form would appear here.\n");
    }

    private void findItem() {
        terminalArea.append("\n=== FIND ITEM ===\n");
        // Implementation would go here
        terminalArea.append("Item search functionality would appear here.\n");
    }

    private void removeItem() {
        terminalArea.append("\n=== REMOVE ITEM ===\n");
        // Implementation would go here
        terminalArea.append("Item removal functionality would appear here.\n");
    }

    // Customer management methods
    private void addCustomer() {
        terminalArea.append("\n=== ADD NEW CUSTOMER ===\n");
        // Implementation would go here
        terminalArea.append("Customer addition form would appear here.\n");
    }

    private void updateCustomer() {
        terminalArea.append("\n=== UPDATE CUSTOMER ===\n");
        // Implementation would go here
        terminalArea.append("Customer update form would appear here.\n");
    }

    private void findCustomer() {
        terminalArea.append("\n=== FIND CUSTOMER ===\n");
        // Implementation would go here
        terminalArea.append("Customer search functionality would appear here.\n");
    }

    private void removeCustomer() {
        terminalArea.append("\n=== REMOVE CUSTOMER ===\n");
        // Implementation would go here
        terminalArea.append("Customer removal functionality would appear here.\n");
    }

    // Report methods
    private void showStockReport() {
        terminalArea.append("\n=== STOCK REPORT ===\n");
        for (Item item : items) {
            terminalArea.append(item.getName() + ": " + item.getQuantity() + " in stock\n");
        }
    }

    private void showOrdersReport() {
        terminalArea.append("\n=== OUTSTANDING ORDERS ===\n");
        // Implementation would go here
        terminalArea.append("Order report functionality would appear here.\n");
    }

    private void showBalanceReport() {
        terminalArea.append("\n=== CUSTOMER BALANCES ===\n");
        // Implementation would go here
        terminalArea.append("Balance report functionality would appear here.\n");
    }

    private void showReceiptsReport() {
        terminalArea.append("\n=== SALES RECEIPTS ===\n");
        for (Sale sale : sales) {
            terminalArea.append(sale.toString() + "\n");
        }
    }

    // Data classes
    class Item {
        private String name;
        private double price;
        private int quantity;

        public Item(String name, double price, int quantity) {
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }

        public String getName() { return name; }
        public double getPrice() { return price; }
        public int getQuantity() { return quantity; }

        @Override
        public String toString() {
            return String.format("%s - $%.2f (Qty: %d)", name, price, quantity);
        }
    }

    class Customer {
        private String name;
        private String email;
        private String address;

        public Customer(String name, String email, String address) {
            this.name = name;
            this.email = email;
            this.address = address;
        }

        @Override
        public String toString() {
            return String.format("%s (%s) - %s", name, email, address);
        }
    }

    class Sale {
        private Customer customer;
        private List<Item> items;
        private double total;

        public Sale(Customer customer, List<Item> items) {
            this.customer = customer;
            this.items = new ArrayList<>(items);
            this.total = items.stream().mapToDouble(item -> item.getPrice()).sum();
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("Sale for %s\n", customer));
            items.forEach(item -> sb.append(String.format("  %s\n", item)));
            sb.append(String.format("TOTAL: $%.2f\n", total));
            return sb.toString();
        }
    }
}
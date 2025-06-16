-- Insertion of model citizens
INSERT INTO `citizens` (first_name, last_name, email, phone, address, created_at) VALUES
('Ján', 'Vážny', 'jan.vazny@example.com', '0901123456', 'Kvetná 15, Bratislava', NOW()),
('Eva', 'Rýchla', 'eva.rychla@example.com', '0902234567', 'Hlavná 1, Košice', NOW()),
('Milan', 'Horváth', 'milan.h@example.com', '0915987654', 'Pod Hradom 22, Trenčín', NOW());

-- Insertion of sample compartments
INSERT INTO `departments` (name, description, contact_email, contact_phone, created_at) VALUES
('Oddelenie technických služieb', 'Správa ciest, chodníkov a verejného osvetlenia.', 'ts@mesto.sk', '055123456', NOW()),
('Oddelenie životného prostredia', 'Správa parkov, zelene a odpadu.', 'zp@mesto.sk', '055123457', NOW()),
('Mestská polícia', 'Riešenie problémov s parkovaním a verejným poriadkom.', 'mp@mesto.sk', '159', NOW());

-- Insertion of sample technicians
INSERT INTO `technicians` (first_name, last_name, email, specialization, available, department_id, created_at) VALUES
('Peter', 'Novák', 'peter.novak@mesto.sk', 'Oprava ciest', 1, 1, NOW()),
('Martin', 'Kováč', 'martin.kovac@mesto.sk', 'Verejné osvetlenie', 1, 1, NOW()),
('Zuzana', 'Zelená', 'zuzana.zelena@mesto.sk', 'Údržba parkov', 1, 2, NOW()),
('Jozef', 'Múdry', 'jozef.mudry@mesto.sk', 'Odpadové hospodárstvo', 0, 2, NOW());

-- Inserting sample stimuli
INSERT INTO `reports` (title, description, location, status, priority, citizen_id, department_id, created_at, updated_at) VALUES
('Poškodený chodník na Kvetnej', 'Na ulici Kvetná je veľká diera v chodníku, hrozí úraz najmä starším občanom.', 'Kvetná 15, Bratislava', 'NEW', 'HIGH', 1, 1, NOW(), NOW()),
('Nefunkčná lampa', 'Lampa verejného osvetlenia na rohu Hlavnej a Mlynskej ulice už týždeň nesvieti.', 'Roh Hlavnej a Mlynskej, Košice', 'NEW', 'MEDIUM', 2, 1, NOW(), NOW()),
('Preplnené kontajnery', 'Kontajnery na separovaný odpad pri OC Galéria sú plné a odpadky sú všade naokolo.', 'Toryská ulica, Košice', 'IN_PROGRESS', 'MEDIUM', 2, 2, NOW(), NOW()),
('Nelegálna skládka', 'Za bytovým domom na adrese Pod Hradom 22 niekto vyhodil starý nábytok.', 'Pod Hradom 22, Trenčín', 'RESOLVED', 'LOW', 3, 2, NOW(), NOW());

-- Assigning a technician to a stimulus (podnet)
-- Assignment of Zuzana Zelená to the complaint about overcrowded containers
INSERT INTO `report_technician` (report_id, technician_id) VALUES
(3, 3);
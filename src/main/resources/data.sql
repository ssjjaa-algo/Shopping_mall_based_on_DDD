-- CATEGORY 테이블에 데이터 삽입
INSERT INTO category (name, depth, parent_id) VALUES ('cloth', 1, null);

-- 자식 카테고리 추가
INSERT INTO category (name, depth, parent_id) VALUES
    ('top', 2, 1),
    ('bottom', 2, 1),
    ('knit', 3, 2),
    ('mantoman',3, 2),
    ('car', 1, null);

-- PRODUCT 테이블에 데이터 삽입
INSERT INTO product (NAME, PRICE, STATUS, STOCK) VALUES
('니트1', 3000, 'SELLING', 30),
('니트2', 3000, 'SELLING', 30),
('맨1', 3000, 'SELLING', 30),
('맨2', 3000, 'SELLING', 30),
('바지1', 3000, 'SELLING', 30),
('차1', 3000, 'SELLING', 30);

INSERT INTO product_category (product_id, category_id) VALUES
(1, 4),
(2, 4),
(3, 5),
(4, 5),
(5, 3),
(6, 6);

-- USER 테이블에 데이터 삽입
INSERT INTO user (CITY, DETAILED_ADDRESS, STREET, ZIPCODE, LOGIN_ID, PHONE_NUMBER) VALUES
    ( '서울시', '강남아파트 999동 999호', '강남구', '강남대로 1234길', 'testId', '010-1234-5678');

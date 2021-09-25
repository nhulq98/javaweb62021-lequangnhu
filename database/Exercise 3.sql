SELECT * FROM estateadvanced62021.assignmentbuilding;
USE estateadvanced62021;

-- output id, fullname
-- table : building, assignmentbuilding, user

-- lấy danh sách all staffs đi tìm sự tồn tại nó trong ds staff của tòa nhà. tìm đc thì set cái value check cho nó thành checked ngc lại thì set NULL
select US.id, US.fullname
,(case 

WHEN USR.userid in 
(select ASB.staffid
FROM assignmentbuilding ASB
WHERE ASB.buildingid = 1 -- bypass paramter 
) THEN 'checked' 
ELSE 'NULL' 

-- WHEN USR.userid = any 
-- (select ASB.staffid
-- FROM assignmentbuilding ASB
-- WHERE ASB.buildingid = 1 -- bypass paramter 
-- ) 
-- THEN 'hehe' 
-- ELSE 'GIOI'

END) AS CHECKED

FROM user US, user_role USR -- select user has role is STAFF
WHERE US.id = USR.userid
AND USR.roleid = 2; -- role staff (default)


-- ===============RESULT Final==================
SELECT US.id, US.fullname
,(case 
WHEN USR.userid in 
(select ASB.staffid
FROM assignmentbuilding ASB
WHERE ASB.buildingid = 1 -- bypass paramter 
) THEN 'checked' 
ELSE 'NULL' 
END) AS CHECKED
FROM user US, user_role USR -- select user has role is STAFF
WHERE US.id = USR.userid
AND USR.roleid = 2 -- role staff (default)

-- gửi ds uer từ front end về để lưu vào đb
-- b1: Lấy ds id staff từ front-end : listIdFromFontend
-- B2: Lấy ds id staff đang quản lý tòa nhà theo buildingId: listIdFromDB
-- B3: Lấy 
-- if this list1 


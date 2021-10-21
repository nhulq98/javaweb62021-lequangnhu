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
AND USR.roleid = 2; -- role staff (default)

-- Gửi ds uer từ front end về để lưu vào đb
-- b1: Lấy ds id staff từ front-end : listIdFromFontend
-- B2: Lấy ds id staff đang quản lý tòa nhà theo buildingId: listIdFromDB
-- B3: Lấy 
-- if this list1 


-- bài 2
--    ============cách 2 chỉ dùng SQL==============
-- gửi ds uer từ front end về để lưu vào đb
-- b1: Lấy ds id staff từ front-end : listIdFromFontend
-- B2: Lấy ds id staff đang quản lý tòa nhà theo buildingId: listIdFromDB
-- B3: Tìm và xóa những phần tử của listIdFromDB ko tồn tai ở listIdFromFontend
-- B4: tim và thêm nhung ptu cua listIdFromFontend ko tồn tại ở listIdFromDB


-- GIAI------
-- B3: Tìm và xóa những phần tử của listIdFromDB ko tồn tai ở listIdFrom Fontend
DELETE FROM assignmentbuilding ASB
WHERE ASB.staffid NOT in (2, 3, 4)# danh sách listIdFrom Fontend
  AND ASB.buildingid = 1; -- by pass paramter

-- B4: tìm và thêm những ptu cua listIdFromFront-end ko tồn tại ở listIdFromDB of 1 specife building
-- B4.1: FIND staffs(listIdFromFront-end) not exists in listIdFromDB(list staff is managing building) with buildingId = 10;
INSERT INTO assignmentbuilding(buildingid, staffid)
SELECT temp.buildingid, temp.staffid
FROM (SELECT 10 as buildingid, US.id as staffid # truyền buildingid vào select
      FROM USER US
      WHERE 1=1 IN (US.id = 2, US.id = 3) # danh sách listIdFrom Fontend
     ) temp
WHERE temp.staffid
      NOT IN (SELECT ASB.staffid FROM assignmentbuilding ASB WHERE ASB.buildingid = 10); # truyền buildingid vào

-- Lưu ý: khi dùng cách này ta phải áp dụng stored produced
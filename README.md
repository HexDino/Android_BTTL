# Student Manager Plus - Multiple Activities

## Mô tả
Ứng dụng quản lý sinh viên sử dụng nhiều Activity để quản lý thông tin sinh viên với các chức năng đầy đủ.

## Cấu trúc ứng dụng

### 🏠 MainActivity - Danh sách sinh viên
- Hiển thị danh sách sinh viên trong RecyclerView
- Mỗi item chỉ hiển thị **MSSV** và **Họ tên**
- Option menu với nút "Thêm sinh viên"
- Click vào sinh viên → Mở StudentDetailActivity

### ➕ AddStudentActivity - Thêm sinh viên mới
- Form nhập thông tin sinh viên mới:
  - Mã số sinh viên (MSSV)
  - Họ và tên
  - Số điện thoại
  - Địa chỉ
- Validation đầy đủ cho tất cả các trường
- Nút Lưu: Thêm sinh viên và quay về MainActivity
- Nút Hủy: Đóng activity

### 📋 StudentDetailActivity - Chi tiết & Cập nhật
- Hiển thị thông tin chi tiết sinh viên
- MSSV: Chỉ hiển thị (không cho phép chỉnh sửa)
- Các trường khác có thể chỉnh sửa:
  - Họ và tên
  - Số điện thoại
  - Địa chỉ
- Nút Cập Nhật: Lưu thay đổi và quay về MainActivity
- Nút Hủy: Đóng activity

## Luồng hoạt động

```
┌─────────────────┐
│  MainActivity   │ ← Launcher Activity
│  (Danh sách)    │
└────────┬────────┘
         │
         ├─ [Menu] ─────────────┐
         │                      ▼
         │              ┌──────────────────┐
         │              │ AddStudentActivity│
         │              │  (Thêm mới)       │
         │              └──────────────────┘
         │                      │
         │                      │ (Return result)
         │ ◄────────────────────┘
         │
         ├─ [Click item] ───────┐
         │                      ▼
         │              ┌──────────────────────┐
         │              │ StudentDetailActivity│
         │              │  (Chi tiết & Update) │
         │              └──────────────────────┘
         │                      │
         │                      │ (Return result)
         │ ◄────────────────────┘
         ▼
```

## Model dữ liệu

### Student.kt
```kotlin
data class Student(
    val id: String,        // MSSV (không thay đổi)
    var name: String,      // Họ tên
    var phone: String,     // Số điện thoại
    var address: String    // Địa chỉ
) : Serializable
```

## Công nghệ sử dụng

### Architecture
- **Multiple Activities** - Mỗi màn hình là một Activity riêng
- **Intent & Serializable** - Truyền dữ liệu giữa các Activity
- **startActivityForResult** - Nhận kết quả từ Activity con

### UI Components
- **RecyclerView** - Hiển thị danh sách sinh viên
- **TextInputLayout** - Form input với Material Design
- **Option Menu** - Menu trên ActionBar
- **ScrollView** - Cho phép scroll trong form

### Kotlin Features
- **Data Class** - Model sinh viên
- **Lambda Functions** - Click listeners
- **Companion Object** - Request codes
- **Intent Extras** - Truyền dữ liệu

## Tính năng

### ✅ Chức năng chính
1. **Xem danh sách** sinh viên (MSSV + Họ tên)
2. **Thêm sinh viên** mới qua AddStudentActivity
3. **Xem chi tiết** sinh viên qua StudentDetailActivity
4. **Cập nhật thông tin** sinh viên
5. **Validation** đầy đủ cho tất cả form

### 🎨 UI/UX
- Material Design với TextInputLayout
- Color coding cho các button:
  - 🟢 Lưu (Green - #4CAF50)
  - 🔵 Cập nhật (Blue - #2196F3)
  - 🔴 Hủy (Red - #F44336 / Gray - #9E9E9E)
- ScrollView cho form dài
- Back button trên ActionBar
- Divider giữa các item trong danh sách

## Cấu trúc project

```
app/src/main/
├── java/com/example/studentmanagerplus/
│   ├── MainActivity.kt              # Danh sách sinh viên
│   ├── AddStudentActivity.kt        # Thêm sinh viên
│   ├── StudentDetailActivity.kt     # Chi tiết & cập nhật
│   ├── Student.kt                   # Data class
│   └── StudentAdapter.kt            # RecyclerView Adapter
└── res/
    ├── layout/
    │   ├── activity_main.xml           # Layout danh sách
    │   ├── item_student.xml            # Layout item
    │   ├── activity_add_student.xml    # Layout thêm
    │   └── activity_student_detail.xml # Layout chi tiết
    └── menu/
        └── menu_main.xml               # Option menu
```

## Yêu cầu hệ thống
- Android API Level 26+ (Android 8.0 Oreo)
- Compile SDK: 34
- Target SDK: 34
- Kotlin: 1.9.0

## Hướng dẫn sử dụng

### 1. Xem danh sách
- Mở app → Hiển thị MainActivity với danh sách 5 sinh viên mẫu

### 2. Thêm sinh viên mới
- Nhấn icon **+** trên ActionBar
- Nhập đầy đủ thông tin (MSSV, Họ tên, SĐT, Địa chỉ)
- Nhấn **Lưu** → Sinh viên mới xuất hiện trong danh sách

### 3. Xem chi tiết và cập nhật
- Click vào một sinh viên trong danh sách
- Xem thông tin chi tiết
- Chỉnh sửa Họ tên, SĐT, hoặc Địa chỉ
- Nhấn **Cập Nhật** → Thông tin được cập nhật trong danh sách

## Dữ liệu mẫu
5 sinh viên có sẵn:
1. Nguyễn Văn A - 20200001 - 0901234567 - Hà Nội
2. Trần Thị B - 20200002 - 0902345678 - Hồ Chí Minh
3. Lê Văn C - 20200003 - 0903456789 - Đà Nẵng
4. Phạm Thị D - 20200004 - 0904567890 - Hải Phòng
5. Hoàng Văn E - 20200005 - 0905678901 - Cần Thơ

## So sánh với bài tập cũ

| Tính năng | Bài cũ | Bài mới |
|-----------|--------|---------|
| Số Activity | 1 | 3 |
| Thêm sinh viên | Trong MainActivity | AddStudentActivity riêng |
| Xem chi tiết | Không có | StudentDetailActivity riêng |
| Cập nhật | Trong MainActivity | StudentDetailActivity |
| Xóa | Có icon xóa | Không có (tập trung vào CRUD khác) |
| Số trường dữ liệu | 2 (MSSV, Họ tên) | 4 (MSSV, Họ tên, SĐT, Địa chỉ) |
| Navigation | Không có | Intent navigation giữa Activities |

## Điểm nổi bật

✅ **Separation of Concerns** - Mỗi activity có trách nhiệm riêng
✅ **Material Design** - TextInputLayout, proper colors
✅ **Data Transfer** - Serializable & Intent extras
✅ **Result Handling** - startActivityForResult pattern
✅ **Validation** - Đầy đủ cho tất cả form
✅ **Back Navigation** - Parent activity configuration
✅ **User-friendly** - Toast messages, focus management

## Hướng dẫn build và chạy
1. Clone repository
2. Mở project trong Android Studio
3. Sync Gradle
4. Run trên emulator hoặc thiết bị thật

## Tác giả
Android Development - Week 8 Exercise 4
Multiple Activities Pattern - Student Management System

